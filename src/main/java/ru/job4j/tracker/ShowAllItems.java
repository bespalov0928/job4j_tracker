package ru.job4j.tracker;

import java.util.List;

public class ShowAllItems implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = tracker.findAll();
        if (items.size() == 0) {
            System.out.print("Нет ни одной заявки.");
        }
        for (Item item : items) {
            System.out.print(item.getName()+System.lineSeparator());
        }
        return true;
    }
}
