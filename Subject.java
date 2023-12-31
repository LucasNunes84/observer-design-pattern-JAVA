package Observer;

// Interface Subject que define métodos para adicionar, remover e notificar observadores
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
