import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdentityFunctionTest {
    @Test
    public void testApply() {
        IdentityFunction f = new IdentityFunction();
        assertEquals(0.0, f.apply(0.0), 1e-10);
        assertEquals(1.0, f.apply(1.0), 1e-10);
        assertEquals(-1.0, f.apply(-1.0), 1e-10);
        assertEquals(123456789.0, f.apply(123456789.0), 1e-10);
    }
}