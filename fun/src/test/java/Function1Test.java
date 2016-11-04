import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Function1Test {
    @Test
    public void compose() throws Exception {
        Function1<Integer, String> intToStr = Object::toString;
        Function1<CharSequence, Integer> len = CharSequence::length;
        Function1<Integer, Integer> numbOfDigits = intToStr.compose(len);
        assertEquals((Integer) 3, numbOfDigits.apply(239));
    }
}