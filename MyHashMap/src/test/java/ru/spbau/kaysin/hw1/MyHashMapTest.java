package ru.spbau.kaysin.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {
    @Test
    public void contains() throws Exception {
        MyHashMap x = new MyHashMap();
        x.put("1", ":)");
        assertTrue(x.contains("1"));
        assertFalse(x.contains("2"));
    }

    @Test
    public void get() throws Exception {
        MyHashMap x = new MyHashMap();
        x.put("1", ":)");
        assertEquals(":)", x.get("1"));
        assertNull(x.get("2"));
        x.put("2", ":))");
        assertEquals(":))", x.put("2", ":(("));
    }

    @Test
    public void put() throws Exception {
        MyHashMap x = new MyHashMap();
        assertNull(x.put("1", ":)"));
        assertEquals(":)", x.put("1", ":("));
        assertEquals(":(", x.get("1"));
    }

    @Test
    public void remove() throws Exception {
        MyHashMap x = new MyHashMap();
        x.put("1", ":)");
        x.put("3", ":)))");
        assertNull(x.remove("2"));
        x.put("2", ":))");
        assertEquals(":)", x.remove("1"));
        assertNull(x.remove("1"));
    }

    @Test
    public void clear() throws Exception {
        MyHashMap x = new MyHashMap();
        x.put("1", ":)");
        x.put("2", ":))");
        x.put("3", ":)))");
        assertTrue(x.contains("1"));
        assertTrue(x.contains("2"));
        assertTrue(x.contains("3"));
        x.clear();
        assertFalse(x.contains("1"));
        assertFalse(x.contains("2"));
        assertFalse(x.contains("3"));
    }

    @Test
    public void size() throws Exception {
        MyHashMap x = new MyHashMap();
        assertEquals(0, x.size());
        x.put("1", ":)");
        assertEquals(1, x.size());
        x.put("2", ":))");
        assertEquals(2, x.size());
        x.put("3", ":)))");
        assertEquals(3, x.size());
        x.remove("1");
        assertEquals(2, x.size());
    }


}