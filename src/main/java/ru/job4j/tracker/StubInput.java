package ru.job4j.tracker;

public class StubInput implements Input {
    @Override
    public String askStr(String question) {
        return null;
    }

    @Override
    public int askInt(String question) {
        return 0;
    }

    @Override
    public int askInt(String question, int max) {
        return 0;
    }
}
