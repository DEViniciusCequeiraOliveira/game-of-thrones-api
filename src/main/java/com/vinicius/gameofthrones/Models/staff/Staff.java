package com.vinicius.gameofthrones.Models.staff;

public abstract class Staff {
    protected String name;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
