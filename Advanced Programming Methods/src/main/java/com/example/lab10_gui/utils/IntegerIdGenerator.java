package com.example.lab10_gui.utils;

public class IntegerIdGenerator {
    private Integer idCount;

    public IntegerIdGenerator () {
        this.idCount=0;
    }


    public void setIdCount(Integer idCount) {
        this.idCount=idCount;
    }

    /**
     * Returns a new valid id
     * @return the new generated id
     */
    public Integer generateId() {
        this.idCount++;
        return this.idCount;
    }
}
