package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {

    private List<String> answer;
    private int position = 0;

    public StubInput(List<String> answer) {
        this.answer = answer;
    }

    public String askStr(String question) {
        return answer.get(position++);
    }

    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        return askInt(question);
    }

}
