import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;


public class CollectionsTest {
    @Test
    public void map() throws Exception {
        Function1<Integer, Integer> square = (x -> x * x);
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Iterable<Integer> sqIt = Collections.map(square, numbers);
        ArrayList<Integer> sqList = new ArrayList<>();
        sqIt.forEach(sqList::add);

        assertEquals(3, sqList.size());
        assertEquals((Integer)1, sqList.get(0));
        assertEquals((Integer)4, sqList.get(1));
        assertEquals((Integer)9, sqList.get(2));
    }

    @Test
    public void filter() throws Exception {
        Predicate<Integer> odd = (x -> x % 2 != 0);
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Iterable<Integer> oddIt = Collections.filter(odd, numbers);
        ArrayList<Integer> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);
        assertEquals(2, oddList.size());
        assertEquals((Integer)1, oddList.get(0));
        assertEquals((Integer)3, oddList.get(1));
    }

    @Test
    public void takeWhile() throws Exception {
        Predicate<Integer> odd = (x -> x % 2 != 0);
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Iterable<Integer> oddIt = Collections.takeWhile(odd, numbers);
        ArrayList<Integer> oddList = new ArrayList<>();
        oddIt.forEach(oddList::add);
        assertEquals(1, oddList.size());
        assertEquals((Integer)1, oddList.get(0));
    }

    @Test
    public void takeUnless() throws Exception {
        Predicate<Integer> pos = (x -> x > 0);
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(-2);
        numbers.add(-1);
        numbers.add(1);

        Iterable<Integer> posIt = Collections.takeUnless(pos, numbers);
        ArrayList<Integer> posList = new ArrayList<>();
        posIt.forEach(posList::add);
        assertEquals(2, posList.size());
        assertEquals((Integer)(-2), posList.get(0));
        assertEquals((Integer)(-1), posList.get(1));
    }


    @Test
    public void takeWhileBorderCases() throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Iterable<Integer> allIt = Collections.takeWhile(Predicate.ALWAYS_TRUE, numbers);
        ArrayList<Integer> allList = new ArrayList<>();
        allIt.forEach(allList::add);

        Iterable<Integer> noIt = Collections.takeWhile(Predicate.ALWAYS_FALSE, numbers);
        ArrayList<Integer> noList = new ArrayList<>();
        noIt.forEach(noList::add);

        assertEquals(3, allList.size());
        assertEquals(0, noList.size());
    }

    @Test
    public void foldL() throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        Integer sum = Collections.foldL((x, y) -> x + y, 0, numbers);
        Integer max = Collections.foldL(Math::max, -1, numbers);

        assertEquals((Integer) 6, sum);
        assertEquals((Integer) 3, max);
    }

    @Test
    public void foldLSpecific() throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

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
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        Integer sum = Collections.foldR((x, y) -> x + y, 0, numbers);
        Integer max = Collections.foldR(Math::max, 0, numbers);

        assertEquals((Integer) 6, sum);
        assertEquals((Integer) 3, max);
    }

    @Test
    public void foldRSpecific() throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

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