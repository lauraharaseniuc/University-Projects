package com.example.lab10_gui.observer_pattern;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObserver(E e);
}
