import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PredicateTest {
    @Test
    public void and() throws Exception {
        Predicate<String> big = x -> x.length() > 4;
        Predicate<CharSequence> odd = x -> (x.length() % 2 != 0);
        Predicate<String> posAndOdd = big.and(odd);
        assertTrue(posAndOdd.apply("abcde"));
        assertFalse(posAndOdd.apply("abcdef"));
        assertFalse(posAndOdd.apply("abc"));
        assertFalse(posAndOdd.apply("ab"));
    }

    @Test
    public void or() throws Exception {
        Predicate<String> big = x -> x.length() > 4;
        Predicate<CharSequence> odd = x -> (x.length() % 2 != 0);
        Predicate<String> posAndOdd = big.or(odd);
        assertTrue(posAndOdd.apply("abcde"));
        assertTrue(posAndOdd.apply("abcdef"));
        assertTrue(posAndOdd.apply("abc"));
        assertFalse(posAndOdd.apply("ab"));
    }

    @Test
    public void not() throws Exception {
        Predicate<Integer> pos = x -> x > 0;
        Predicate<Integer> notPos = pos.not();
        assertTrue(notPos.apply(0));
        assertFalse(notPos.apply(1));
    }

    @Test
    public void constants() {
        Predicate<Object> fls = Predicate.ALWAYS_FALSE;
        assertFalse(fls.apply(5));

        Predicate<Object> tru = Predicate.ALWAYS_TRUE;
        assertTrue(tru.apply(5));
    }

    @Test
    public void AndLazinessTest() {
        Predicate<Object> tru = Predicate.ALWAYS_TRUE;
        Predicate<Object> bot = x -> {
            fail();
            return false;
        };

        Predicate<Object> mustBeTrue = tru.or(bot);

        assertTrue(mustBeTrue.apply(1));
    }

    @Test
    public void OrLazinessTest() {
        Predicate<Object> fls = Predicate.ALWAYS_FALSE;
        Predicate<Object> bot = x -> {
            fail();
            return false;
        };

        Predicate<Object> mustBeFalse = fls.and(bot);

        assertFalse(mustBeFalse.apply(1));
    }
}