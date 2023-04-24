package com.example.lab10_gui.keys;

import java.util.Objects;

public class TwoDKey<KEY> {
    private final KEY key1;
    private final KEY key2;

    public TwoDKey(KEY value1, KEY key2) {
        this.key1 = value1;
        this.key2 = key2;
    }

    public KEY getKey1() {
        return this.key1;
    }

    public KEY getKey2() {
        return this.key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoDKey<?> twoDKey = (TwoDKey<?>) o;
        return key1.equals(twoDKey.key1) && key2.equals(twoDKey.key2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key1, key2);
    }
}
