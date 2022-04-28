package ru.job4j.question;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class InfoTest {

    @Test
    public void whenAddChange() {
        Info info = new Info(1, 2, 3);
        info.addChange();
        info.addChange();
        info.addChange();
        assertThat(info.getChanged(), is(5));
    }

    @Test
    public void whenAddDelete() {
        Info info = new Info(1, 2, 3);
        info.addDelete();
        assertThat(info.getDeleted(), is(4));
    }

    @Test
    public void whenAddAdded() {
        Info info = new Info(1, 2, 3);
        info.addAdded();
        assertThat(info.getAdded(), is(2));
    }
}