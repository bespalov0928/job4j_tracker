package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditItemsTest {

    @Test
    public void execute() {

       Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditItems rep = new EditItems();

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(rep.getOut(), is("=== Edit item ====" + ln + "Edit item is done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));

    }


    @Test
    public void executeNot() {

        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        EditItems rep = new EditItems();
        Input input = mock(Input.class);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(rep.getOut(), is("=== Edit item ====" + ln + "Item with id=0 not found." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));

    }
}