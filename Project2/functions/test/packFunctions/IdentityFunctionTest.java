package packFunctions;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdentityFunctionTest {
    @Test
    public void testApply() {
        IdentityFunction f = new IdentityFunction();
        assertEquals(0.0, f.apply(0.0), 0.0);
        assertEquals(1.0, f.apply(1.0), 0.0);
        assertEquals(-1.0, f.apply(-1.0), 0.0);
        assertEquals(123456789.0, f.apply(123456789.0), 0.0);
    }

    @Test
    public void testToString() {
        IdentityFunction f = new IdentityFunction();
        assertEquals("IdentityFunction", f.toString());
    }

    @Test
    public void testEquals() {
        IdentityFunction f = new IdentityFunction();
        IdentityFunction f2 = new IdentityFunction();
        ConstantFunction f3 = new ConstantFunction(5);
        assertTrue(f.equals(f2));
        assertFalse(f.equals(f3));
    }

    @Test
    public void testHashCode() {
        IdentityFunction f = new IdentityFunction();
        IdentityFunction f2 = new IdentityFunction();
        assertNotEquals(f.hashCode(), f2.hashCode());
    }
    @Test
    public void testClone() throws CloneNotSupportedException {
        IdentityFunction f = new IdentityFunction();
        IdentityFunction f2 = (IdentityFunction) f.clone();
        assertEquals(f, f2);
    }
}

