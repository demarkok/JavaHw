import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CollectionsTest {
    @Test
    public void map() throws Exception {
        Function1<CharSequence, Integer> len = CharSequence::length;
        List<String> numbers = Arrays.asList("12", "239", "4");
        Iterable<Integer> sqIt = Collections.map(len, numbers);

        List<Integer> sqList = new ArrayList<>();
        sqIt.forEach(sqList::add);

        assertEquals(Arrays.asList(2, 3, 1), sqList);
    }


    @Test
    public void filter() throws Exception {
        Predicate<CharSequence> oddLen = (x -> x.length() % 2 != 0);
        List<String> numbers = Arrays.asList("12", "239", "4");

        Iterable<String> oddIt = Collections.filter(oddLen, numbers);
        List<String> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);

        assertEquals(Arrays.asList("239", "4"), oddList);
    }


    @Test
    public void takeWhile() throws Exception {
        Predicate<CharSequence> oddLen = (x -> x.length() % 2 != 0);
        List<String> numbers = Arrays.asList("123", "2", "42");

        Iterable<String> oddIt = Collections.takeWhile(oddLen, numbers);
        List<String> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);

        assertEquals(Arrays.asList("123", "2"), oddList);
    }


    @Test
    public void takeUnless() throws Exception {
        Predicate<CharSequence> oddLen = (x -> x.length() % 2 != 0);
        List<String> numbers = Arrays.asList("1234", "22", "4");

        Iterable<String> oddIt = Collections.takeUnless(oddLen, numbers);
        List<String> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);

        assertEquals(Arrays.asList("1234", "22"), oddList);
    }


    @Test
    public void takeWhileBorderCases() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Iterable<Integer> allIt = Collections.takeWhile(Predicate.ALWAYS_TRUE, numbers);
        List<Integer> allList = new ArrayList<>();
        allIt.forEach(allList::add);

        Iterable<Integer> noIt = Collections.takeWhile(Predicate.ALWAYS_FALSE, numbers);
        List<Integer> noList = new ArrayList<>();
        noIt.forEach(noList::add);

        assertEquals(Arrays.asList(1, 2, 3), allList);
        assertTrue(noList.isEmpty());
    }


    @Test
    public void foldL() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Function2<List<String>, Object, ArrayList<String>> foo =
                (seq, x) -> {
                    ArrayList<String> nSeq = new ArrayList<>(seq);
                    nSeq.add(x.toString());
                    return nSeq;
                };

        ArrayList<String> strings = Collections.foldL(foo, new ArrayList<>(), numbers);

        assertEquals(Arrays.asList("1", "2", "3"), strings);
    }

    @Test
    public void foldR() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Function2<Object, List<String>, ArrayList<String>> foo =
                (x, seq) -> {
                    ArrayList<String> nSeq = new ArrayList<>(seq);
                    nSeq.add(x.toString());
                    return nSeq;
                };

        ArrayList<String> strings = Collections.foldR(foo, new ArrayList<>(), numbers);

        assertEquals(Arrays.asList("3", "2", "1"), strings);
    }
}