package ru.job4j.tracker;

import java.util.List;

public class FindItemsByName implements UserAction {

    private String out = "";

    private void setOut(String out) {
        this.out = out;
    }

    public String getOut() {
        return out;
    }

    @Override
    public String name() {
        return "=== Find items by name ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter Name");
        List<Item> namesItem = tracker.findByName(name);
        if (namesItem.size() > 0) {
            setOut("=== Find items by name ===="+System.lineSeparator()+"Edit item is done."+System.lineSeparator());
        } else {
            setOut("=== Find items by name ===="+System.lineSeparator()+ String.format("Item with name=%s not found.", name)+System.lineSeparator());
//            for (Item item : namesItem) {
//                System.out.print(item);
//            }
        }

        return true;
    }
}
