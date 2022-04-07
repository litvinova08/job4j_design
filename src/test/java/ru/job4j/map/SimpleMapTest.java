package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenGet2and4() {
        SimpleMap<String, String> table = new SimpleMap<>();
        table.put("1", "aleks");
        table.put("4", "elena");
        table.put("5", "rita");
        assertThat(table.get("5"), is("rita"));
        assertThat(table.get("4"), is("elena"));
    }

    @Test
    public void when5AddedThen2Deleted() {
        SimpleMap<String, String> table = new SimpleMap<>();
        table.put("1", "aleks");
        table.put("4", "elena");
        table.put("5", "rita");
        table.put("6", "rita");
        table.put("8", "rita");
        table.remove("1");
        table.remove("8");
        assertThat(table.getCount(), is(3));
    }

    @Test
    public void when3AddedThen1Deleted() {
        SimpleMap<String, String> table = new SimpleMap<>();
        table.put("1", "aleks");
        table.put("4", "elena");
        table.put("5", "rita");
        table.remove("1");
        assertNull(table.get("1"));
    }

    @Test
    public void whenIteratorUsed() {
        SimpleMap<String, String> table = new SimpleMap<>();
        table.put("1", "aleks");
        table.put("4", "elena");
        table.put("5", "rita");
        Iterator<String> iterator = table.iterator();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }
}