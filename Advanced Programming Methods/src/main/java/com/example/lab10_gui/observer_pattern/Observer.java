package com.example.lab10_gui.observer_pattern;

public interface Observer<E extends Event> {
    void update(E e);
}
