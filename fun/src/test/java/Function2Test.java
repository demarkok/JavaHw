import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {

    @Test
    public void compose() throws Exception {
        Function2<String, String, String> concat = (x, y) -> x + y;
        Function1<String, Integer> length = String::length;
        Function2<String, String, Integer> concatLength = concat.compose(length);
        assertEquals((Integer)8, concatLength.apply("8", "symbols"));
    }

    @Test
    public void bind1() throws Exception {
        Function2<String, String, String> concat = (x, y) -> x + y;
        Function1<String, String> dr = concat.bind1("Dr. ");
        assertEquals("Dr. Strangelove", dr.apply("Strangelove"));
    }

    @Test
    public void bind2() throws Exception {
        Function2<String, String, String> concat = (x, y) -> x + y;
        Function1<String, String> derivative = concat.bind2("'");
        assertEquals("function'", derivative.apply("function"));
    }

    @Test
    public void curry() throws Exception {
        Function2<Integer, Integer, Integer> idStrange = (x, y) -> x;
        Function1<Integer, Function1<Integer, Integer>> id = idStrange.curry();

        Function1<Integer, Integer> id239 = id.apply(239);
        assertEquals((Integer)239, id239.apply(179));
        assertEquals((Integer)239, id239.apply(57));
    }

}