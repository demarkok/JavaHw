package ru.spbau.kaysin.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyNodeTest {
    @Test
    public void insertBefore() throws Exception {
        MyNode x = new MyNode("0", ")");
        MyNode y = new MyNode("1", ":)");
        MyNode z = new MyNode("2", ":))");

        assertSame(x.insertBefore(y), y);
        assertSame(y.insertBefore(z), z);

        assertNull(z.prev);
        assertSame(z.next, y);
        assertSame(y.prev, z);
        assertSame(y.next, x);
        assertSame(x.prev, y);
        assertNull(x.next);
    }

    @Test
    public void remove() throws Exception {
        MyNode x = new MyNode("0", ")");
        MyNode y = new MyNode("1", ":)");
        MyNode z = new MyNode("2", ":))");
        MyNode t = new MyNode("3", ":)))");


        x.insertBefore(y);
        y.insertBefore(t);
        t.insertBefore(z);

        t.remove();

        assertNull(z.prev);
        assertSame(z.next, y);
        assertSame(y.prev, z);
        assertSame(y.next, x);
        assertSame(x.prev, y);
        assertNull(x.next);
    }

}