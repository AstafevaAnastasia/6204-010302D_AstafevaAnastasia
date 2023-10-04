import static org.junit.Assert.*;
import org.junit.Test;

public class MockTabulatedFunctionTest {

    private static final double EPSILON = 1e-10;
    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction function = new MockTabulatedFunction(-1.0, 1.0, -1.0, 1.0);
        assertEquals(0.0, function.interpolate(0.0, 0), EPSILON);
        assertEquals(-0.5, function.interpolate(-0.5, 0), EPSILON);
        assertEquals(0.5, function.interpolate(0.5, 0), EPSILON);
    }

    @Test
    public void testApply() {
        AbstractTabulatedFunction function = new MockTabulatedFunction(-1.0, 1.0, -1.0, 1.0);
        assertEquals(1.0, function.apply(0.0), EPSILON);
        assertEquals(-1.0, function.apply(-2.0), EPSILON);
        assertEquals(1.0, function.apply(2.0), EPSILON);
    }

}