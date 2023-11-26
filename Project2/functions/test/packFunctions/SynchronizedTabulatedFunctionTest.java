package packFunctions;
import concurrent.SynchronizedTabulatedFunction;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    @Test
    public void testIterator() {
        TabulatedFunction function = new LinkedListTabulatedFunction(new double[]{0, 1, 2, 3, 4}, new double[]{0, 1, 4, 9, 16});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);

        Iterator<Point> iterator = synchronizedFunction.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(new Point(0, 0), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Point(1, 1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Point(2, 4), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Point(3, 9), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Point(4, 16), iterator.next());
        assertFalse(iterator.hasNext());
    }
}
