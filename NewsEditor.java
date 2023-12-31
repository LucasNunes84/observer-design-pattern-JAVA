package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Classe concreta Subject (Editor de Notícias)
class NewsEditor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<News> newsList = new ArrayList<>();
    private String latestNews;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = 0;
        for(i=0; i<observers.size(); i++){
            if(observer.toString().equals((observers.get(i)).toString())){
                observers.remove(i);
            }
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(latestNews));
    }

    // Método para publicar uma nova notícia
    public void publishNews(String title, String news) {
        this.latestNews = title;
        newsList.add(new News(title, news));
        notifyObservers();
    }

    // Função que retorna um objeto News com base no título fornecido
    public News searchNews(String titleToFind){
        Optional<News> foundNews = newsList.stream()
                .filter(newsItem -> newsItem.getTitle().equals(titleToFind))
                .findFirst();

        return foundNews.orElse(null);
    }

    //Retorna uma lista de títulos de notícias criadas
    public List<String> getNewsTitles(){
        List<String> titleList = new ArrayList<>();

        newsList.forEach(objeto -> titleList.add(objeto.getTitle()));

        return titleList;
    }
}