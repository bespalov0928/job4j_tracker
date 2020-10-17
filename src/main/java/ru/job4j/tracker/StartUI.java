package ru.job4j.tracker;

import java.util.List;

public class StartUI {

    public void init(Input input, Store tracker, UserAction[]  actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select:", actions.length);
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {

        System.out.println("Menu.");

        for (int index = 0; index < actions.length; index++) {
            System.out.print(index + ". " + actions[index].name()+ System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        //System.out.println("start");
        Input validate = new ValidateInput(new ConsoleInput());

        try (Store tracker = new SqlTracker()) {
            tracker.init();
            UserAction[] actions = {
                    new CreateAction(),
                    new EditItems(),
                    new DeleteItem(),
                    new ShowAllItems(),
                    new FindItemById(),
                    new FindItemsByName()
            };
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
