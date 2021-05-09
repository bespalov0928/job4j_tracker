package ru.job4j.tracker;

public class FindItemById implements UserAction {

    private String out = "";

    private void setOut(String out) {
        this.out = out;
    }

    public String getOut() {
        return out;
    }

    @Override
    public String name() {
        return "=== Find item by Id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        int id = input.askInt("Enter Id.");
        Item item = tracker.findById(id);
        if (item != null) {
            setOut("=== Find item by Id ===="+System.lineSeparator()+"Edit item is done."+System.lineSeparator());
            //System.out.println(item);
        } else {
            setOut("=== Find item by Id ===="+System.lineSeparator()+ String.format("Item with id=%s not found.", id)+System.lineSeparator());
            //System.out.println("Заявка не найдена");
        }
        return true;
    }
}
