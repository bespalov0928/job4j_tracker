package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindItemsByNameTest {

    @Test
    public void execute() {
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String findName = "Replaced item";
        FindItemsByName rep = new FindItemsByName();

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(findName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(rep.getOut(), is("=== Find items by name ====" + ln + "Edit item is done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(findName));
    }

    @Test
    public void executeNot() {
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String findName = "Replaced item";
        FindItemsByName rep = new FindItemsByName();

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("");

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(rep.getOut(), is("=== Find items by name ====" + ln + "Item with name= not found." + ln));
    }
}