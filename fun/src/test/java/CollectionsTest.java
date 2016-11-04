import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CollectionsTest {
    @Test
    public void map() throws Exception {
        Function1<Integer, Integer> square = (x -> x * x);
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Iterable<Integer> sqIt = Collections.map(square, numbers);

        List<Integer> sqList = new ArrayList<>();
        sqIt.forEach(sqList::add);

        assertEquals(Arrays.asList(1, 4, 9), sqList);
    }


    @Test
    public void filter() throws Exception {
        Predicate<Integer> odd = (x -> x % 2 != 0);
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Iterable<Integer> oddIt = Collections.filter(odd, numbers);
        List<Integer> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);

        assertEquals(Arrays.asList(1, 3), oddList);
    }


    @Test
    public void takeWhile() throws Exception {
        Predicate<Integer> odd = (x -> x % 2 != 0);
        List<Integer> numbers = Arrays.asList(-1, 1, 2, 3);

        Iterable<Integer> oddIt = Collections.takeWhile(odd, numbers);
        List<Integer> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);

        assertEquals(Arrays.asList(-1, 1), oddList);
    }


    @Test
    public void takeUnless() throws Exception {
        Predicate<Integer> pos = (x -> x > 0);
        List<Integer> numbers = Arrays.asList(-2, -1, 1);

        Iterable<Integer> posIt = Collections.takeUnless(pos, numbers);
        List<Integer> posList = new ArrayList<>();
        posIt.forEach(posList::add);

        assertEquals(Arrays.asList(-2, -1), posList);
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

        Integer sum = Collections.foldL((x, y) -> x + y, 0, numbers);
        Integer max = Collections.foldL(Math::max, -1, numbers);

        assertEquals((Integer) 6, sum);
        assertEquals((Integer) 3, max);
    }


    @Test
    public void foldLSpecific() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        LinkedHashSet<Integer> s = Collections.foldL((set, x) -> {
                    LinkedHashSet<Integer> nSet = new LinkedHashSet<>(set);
                    nSet.add(x);
                    return nSet;
                },
                new LinkedHashSet<Integer>(),
                numbers);

        assertEquals(new LinkedHashSet<>(numbers), s);
    }


    @Test
    public void foldR() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Integer sum = Collections.foldR((x, y) -> x + y, 0, numbers);
        Integer max = Collections.foldR(Math::max, 0, numbers);

        assertEquals((Integer) 6, sum);
        assertEquals((Integer) 3, max);
    }


    @Test
    public void foldRSpecific() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        LinkedHashSet<Integer> s = Collections.foldR((x, set) -> {
                    LinkedHashSet<Integer> nSet = new LinkedHashSet<>(set);
                    nSet.add(x);
                    return nSet;
                },
                new LinkedHashSet<Integer>(),
                numbers);

        java.util.Collections.reverse(numbers);
        assertEquals(new LinkedHashSet<>(numbers), s);
    }
}