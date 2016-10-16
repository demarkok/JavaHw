import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void or() throws Exception {
        Predicate<Integer> pos = x -> x > 0;
        Predicate<Integer> odd = x -> (x % 2 != 0);
        Predicate<Integer> posAndOdd = pos.and(odd);
        assertTrue(posAndOdd.apply(3));
        assertFalse(posAndOdd.apply(6));
        assertFalse(posAndOdd.apply(-1));
        assertFalse(posAndOdd.apply(-2));
    }

    @Test
    public void and() throws Exception {
        Predicate<Integer> pos = x -> x > 0;
        Predicate<Integer> odd = x -> (x % 2 != 0);
        Predicate<Integer> posAndOdd = pos.or(odd);
        assertTrue(posAndOdd.apply(3));
        assertTrue(posAndOdd.apply(6));
        assertTrue(posAndOdd.apply(-1));
        assertFalse(posAndOdd.apply(-2));
    }

    @Test
    public void not() throws Exception {
        Predicate<Integer> pos = x -> x > 0;
        Predicate<Integer> notPos = pos.not();
        assertTrue(notPos.apply(0));
        assertTrue(notPos.apply(-1));
        assertFalse(notPos.apply(1));
    }

    @Test
    public void constants() {
        Predicate<Integer> fls = new Predicate.ALWAYS_FALSE <> ();
        assertFalse(fls.apply(5));

        Predicate<Integer> tru = new Predicate.ALWAYS_TRUE <> ();
        assertTrue(tru.apply(5));
    }

    @Test
    public void inheritanceFeatures(){
        Predicate<Object> tru = new Predicate.ALWAYS_TRUE <>();
        Predicate<Integer> pos = x -> x > 0;

        Predicate<Integer> f = pos.and(tru);
//        Predicate<Object> ff = tru.and(pos);  // CE
        assertFalse(f.apply(0));
        assertTrue(f.apply(1));

        Predicate<Integer> g = pos.or(tru);
//        Predicate<Object> gg = tru.or(pos);  // CE
        assertTrue(g.apply(-1));
        assertTrue(g.apply(1));
    }
}