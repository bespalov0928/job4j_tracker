package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemTracker implements Store {
    private final Item[] items = new Item[10];
    private int ids = 1;
    private int size = 0;


    private int generateId() {
        return ids++;
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
    public boolean replace(int id, Item item) {
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                items[index] = item;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Item> findAll() {
        return Arrays.asList(items);
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getName() == key) {
                rsl.add(item);
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

}