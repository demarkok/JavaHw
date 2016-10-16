import org.junit.Test;

import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void compose() throws Exception {
        Function1<Integer, Integer> dbl = x -> x + x;
        Function1<Integer, Integer> halve = x -> x / 2;
        Function1<Integer, Integer> f = dbl.compose(halve);
        assertEquals((Integer) 5, f.apply(5));
        assertEquals((Integer) (-2), f.apply(-2));
    }
}