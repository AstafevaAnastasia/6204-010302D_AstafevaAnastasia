package packFunctions;
import org.junit.Test;
import static org.junit.Assert.*;
public class andThenTest {
    @Test
    public void testApply() {
        MathFunction f = x -> x * 2;
        MathFunction g = x -> x + 3;
        MathFunction h = x -> Math.pow(x, 2);

        MathFunction complexFunction1 = f.andThen(g).andThen(h);
        assertEquals(14.0, complexFunction1.apply(2), 0.0001);

        MathFunction complexFunction2 = g.andThen(h).andThen(f);
        assertEquals(19.0, complexFunction2.apply(2), 0.0001);

        MathFunction complexFunction3 = h.andThen(f).andThen(g);
        assertEquals(100.0, complexFunction3.apply(2), 0.0001);
    }
}

