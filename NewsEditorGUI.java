package Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewsEditorGUI extends JFrame {
    private NewsEditor newsEditor;
    private JTextField titleTextField;
    private JTextArea newsTextField;
    private DefaultListModel<String> newsListModel;
    private JList<String> newsList;

    public NewsEditorGUI() {
        setTitle("Editor de Notícias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        // Criando o editor de notícias
        newsEditor = new NewsEditor();

        // Componentes para adicionar notícias
        titleTextField = new JTextField(20);
        newsTextField = new JTextArea(10,20);
        JButton publishButton = new JButton("Publicar");

        // Lista para mostrar notícias publicadas
        newsListModel = new DefaultListModel<>();
        newsList = new JList<>(newsListModel);
        JScrollPane scrollPane = new JScrollPane(newsList);
        scrollPane.setPreferredSize(new Dimension(200, getHeight()));

        // Botão para abrir Painel com a notícia escolhida
        JButton newsButton = new JButton("Mostrar");

        JButton addSubButton = new JButton("Add Assinante");

        JButton remSubButton = new JButton("Remove Assinante");

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(scrollPane);
        sidePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(sidePanel);

        // Adicionando campo de título e campo de texto para notícia
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Título:"));
        inputPanel.add(titleTextField);
        inputPanel.add(new JLabel("Notícia:"));
        inputPanel.add(newsTextField);
        add(inputPanel);

        // Botão para mostrar notícia já publicada
        newsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                News newsDialog = newsEditor.searchNews(newsList.getSelectedValue());
                JOptionPane.showMessageDialog(null, newsDialog.toString(), newsDialog.getTitle(), 1);
            }
        });
        sidePanel.add(newsButton);

        // Botão para publicar notícia
        publishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String news = newsTextField.getText();
                if (!title.isEmpty() && !news.isEmpty()) {
                    newsEditor.publishNews(title, news);
                    titleTextField.setText("");
                    newsTextField.setText("");
                }
            }
        });
        add(publishButton);

        // Botão para adicionar assinante
        addSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Add Assinante");
                newsEditor.registerObserver(new Subscriber(input));
            }
        });
        add(addSubButton);

        // Botão para remover assinante
        remSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Remove Assinante");
                newsEditor.removeObserver(new Subscriber(input));
            }
        });
        add(remSubButton);

        // Atualizar a lista de notícias quando uma nova notícia for publicada
        newsEditor.registerObserver(new Observer() {
            @Override
            public void update(String news) {
                newsListModel.addElement(news);
            }
        });

        // Definindo layout principal
        setLayout(new FlowLayout());
        pack(); // Ajusta o tamanho da janela para os componentes
        setVisible(true);
    }
}
