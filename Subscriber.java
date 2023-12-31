package Observer;

// Classe concreta Observer (Assinante)
public class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    //Sempre que é publicado uma notícia, no terminal aparece os usuários que receberam ela
    @Override
    public void update(String news) {
        System.out.println(this.name + " recebeu a notícia: " + news);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
