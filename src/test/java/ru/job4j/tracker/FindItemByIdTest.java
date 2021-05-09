package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindItemByIdTest {

    @Test
    public void execute() {
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        FindItemById rep = new FindItemById();

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(rep.getOut(), is("=== Find item by Id ====" + ln + "Edit item is done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
    }

    @Test
    public void executeNot() {
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        FindItemById rep = new FindItemById();

        Input input = mock(Input.class);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(rep.getOut(), is("=== Find item by Id ====" + ln + "Item with id=0 not found." + ln));
    }
}