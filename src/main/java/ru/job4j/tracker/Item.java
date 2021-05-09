package ru.job4j.tracker;

import java.util.Objects;

public class Item {
    private int id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id;'%s', name:'%s'", id, name);
    }
}