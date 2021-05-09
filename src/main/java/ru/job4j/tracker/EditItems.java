package ru.job4j.tracker;

public class EditItems implements UserAction {

    private String out = "";

    private void setOut(String out) {
        this.out = out;
    }

    public String getOut() {
        return out;
    }

    @Override
    public String name() {
        return "=== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String nameItem = input.askStr("Creat item. Enter name");
        int idItem = input.askInt("Creat item. Enter id");
        Item newItem = new Item(nameItem);
        boolean replaceTrue = tracker.replace(idItem, newItem);
        if (replaceTrue){
            setOut("=== Edit item ===="+System.lineSeparator()+"Edit item is done."+System.lineSeparator());
        }else {
            setOut("=== Edit item ===="+System.lineSeparator()+ String.format("Item with id=%s not found.", idItem)+System.lineSeparator());
        }
        return replaceTrue;
    }
}
