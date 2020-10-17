package ru.job4j.tracker;

public class EditItems implements UserAction {
    @Override
    public String name() {
        return "=== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String nameItem = input.askStr("Creat item. Enter name");
        String idItem = input.askStr("Creat item. Enter id");
        Item newItem = new Item(nameItem);
        tracker.replace(idItem, newItem);
        return true;
    }
}
