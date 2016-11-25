import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MyTrieTest {
    @Test
    public void serialize() throws Exception {
        MyTrie t = new MyTrie();
        t.add("foo");
        t.add("bar");
        t.add("fo");
        t.add("f");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        t.serialize(os);
        String[] strings = os.toString().split(" ");
        os.close();

        assertEquals(4, strings.length);
        List<String> stringsList = Arrays.asList(strings);
        assertTrue(stringsList.contains("foo"));
        assertTrue(stringsList.contains("bar"));
        assertTrue(stringsList.contains("fo"));
        assertTrue(stringsList.contains("f"));
    }

    @Test
    public void deserialize() throws Exception {
        String str = "foo bar fo f";

        ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        MyTrie t = new MyTrie();
        t.deserialize(is);
        is.close();

        assertEquals(4, t.size());
        assertTrue(t.contains("foo"));
        assertTrue(t.contains("bar"));
        assertTrue(t.contains("fo"));
        assertTrue(t.contains("f"));

    }

    @Test
    public void add() throws Exception {
        MyTrie t = new MyTrie();
        assertTrue(t.add("foo"));
        assertFalse(t.add("foo"));
        assertTrue(t.add("bar"));
        assertFalse(t.add("bar"));
        assertFalse(t.add("foo"));
    }

    @Test
    public void contains() throws Exception {
        MyTrie t = new MyTrie();
        t.add("foo");
        assertTrue(t.contains("foo"));
        assertFalse(t.contains("f"));
        assertFalse(t.contains("fo"));
        t.add("f");
        assertTrue(t.contains("f"));
        assertFalse(t.contains("fo"));
        t.add("fork");
        assertFalse(t.contains("fo"));
        assertFalse(t.contains("for"));
        assertTrue(t.contains("fork"));
        assertFalse(t.contains("bar"));
    }

    @Test
    public void remove() throws Exception {
        MyTrie t = new MyTrie();
        t.add("foo");
        assertFalse(t.remove("fo"));
        assertTrue(t.contains("foo"));
        assertTrue(t.remove("foo"));
        assertFalse(t.contains("foo"));
        t.add("foo");
        t.add("fork");
        t.add("fo");
        assertTrue(t.contains("foo"));
        assertTrue(t.remove("fo"));
        assertTrue(t.contains("fork"));
        assertFalse(t.contains("fo"));
    }

    @Test
    public void size() throws Exception {
        MyTrie t = new MyTrie();
        assertEquals(0, t.size());
        t.add("foo");
        t.add("foo");
        assertEquals(1, t.size());
        t.add("fork");
        t.remove("fo");
        assertEquals(2, t.size());
        t.add("fork");
        t.add("fo");
        assertEquals(3, t.size());
        t.remove("fo");
        assertEquals(2, t.size());
        t.add("bar");
        assertEquals(3, t.size());
        t.add("f");
        assertEquals(4, t.size());
    }

    @Test
    public void howManyStartsWithPrefix() throws Exception {
        MyTrie t = new MyTrie();
        t.add("foo");
        assertEquals(1, t.howManyStartsWithPrefix("foo"));
        assertEquals(1, t.howManyStartsWithPrefix("f"));
        t.add("foo");
        assertEquals(1, t.howManyStartsWithPrefix(""));
        assertEquals(0, t.howManyStartsWithPrefix("fooo"));
        t.add("fork");
        assertEquals(1, t.howManyStartsWithPrefix("foo"));
        assertEquals(1, t.howManyStartsWithPrefix("for"));
        assertEquals(1, t.howManyStartsWithPrefix("fork"));
        assertEquals(2, t.howManyStartsWithPrefix("fo"));
        t.add("fo");
        assertEquals(3, t.howManyStartsWithPrefix("fo"));
        t.remove("fork");
        assertEquals(0, t.howManyStartsWithPrefix("fork"));
        assertEquals(2, t.howManyStartsWithPrefix("fo"));
        assertEquals(2, t.howManyStartsWithPrefix("f"));
    }

}