package ru.job4j.tracker;

import java.util.List;

public class MemTracker implements Store{
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;


    private String generateId() {
        return String.valueOf(ids++);
    }

    @Override
    public void init() {

    }

    @Override
    public void close() throws Exception {

    }

    public Item add(Item item) {
        item.setId(generateId());
        items[size++] = item;
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByName(String key) {
        return null;
    }

    public Item findById(String id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId().equals(id)) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

}