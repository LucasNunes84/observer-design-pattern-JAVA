package Observer;

import javax.swing.*;

// public class main {
//     public static void main(String[] args) {
//         // Criando o editor de notícias
//         NewsEditor editor = new NewsEditor();

//         // Criando assinantes
//         Subscriber subscriber1 = new Subscriber("Assinante 1");
//         Subscriber subscriber2 = new Subscriber("Assinante 2");

//         // Registrando os assinantes
//         editor.registerObserver(subscriber1);
//         editor.registerObserver(subscriber2);

//         // Publicando uma notícia
//         editor.publishNews("Nova notícia publicada!");

//         // Removendo um assinante
//         editor.removeObserver(subscriber2);

//         // Publicando outra notícia
//         editor.publishNews("Segunda notícia publicada!");
//     }
// }

public class Main{
    public static void main(String[] args) {
        // Executando a aplicação Swing na thread de despacho de eventos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewsEditorGUI();
            }
        });
    }
}
