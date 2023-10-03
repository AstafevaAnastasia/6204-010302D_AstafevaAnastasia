import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SqrFunctionTest {

        private static final double DELTA = 1e-15;
        private final SqrFunction sqrFunction = new SqrFunction();
    @Test
    public void testApply() {
        assertEquals(0.0, sqrFunction.apply(0.0), DELTA);
        assertEquals(1.0, sqrFunction.apply(1.0), DELTA);
        assertEquals(4.0, sqrFunction.apply(2.0), DELTA);
        assertEquals(9.0, sqrFunction.apply(-3.0), DELTA);
        assertEquals(25.0, sqrFunction.apply(5.0), DELTA);
    }
}