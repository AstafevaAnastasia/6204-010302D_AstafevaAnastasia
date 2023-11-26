package packFunctions;
import concurrent.SynchronizedTabulatedFunction;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    TabulatedFunction function = new LinkedListTabulatedFunction(new double[]{0, 1, 2, 3, 4}, new double[]{0, 1, 4, 9, 16});
    SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);

    @Test
    public void testApply() {
        assertEquals(2.5, synchronizedFunction.apply(1.5), 0.0001);
    }

    @Test
    public void testGetCount() {
        assertEquals(5, synchronizedFunction.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(1, synchronizedFunction.getX(1),0.0001);
    }

    @Test
    public void testGetY() {
        assertEquals(4, synchronizedFunction.getY(2),0.0001);
    }

    @Test
    public void testSetY() {
        synchronizedFunction.setY(0, 2);
        assertEquals(2, synchronizedFunction.getY(0), 0.0001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(1, synchronizedFunction.indexOfX(1));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(1, synchronizedFunction.indexOfY(1));
    }

    @Test
    public void testLeftBound() {
        assertEquals(0, synchronizedFunction.leftBound(), 0.0001);
    }

    @Test
    public void testRightBound() {
        assertEquals(4, synchronizedFunction.rightBound(), 0.001);
    }
    @Test
    public void testIterator() {
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

    @Test
    public void testDoSynchronously() {

        SynchronizedTabulatedFunction.Operation<Double> op = synchronizedTabulatedFunction-> {
            double sum = 0;
            for (int i = 0; i < synchronizedFunction.getCount(); i++) {
                sum += synchronizedFunction.getY(i);
            }
            return sum;
        };
        double sum = synchronizedFunction.doSynchronously(op);
        assertEquals(30, sum, 0.00001);
    }
}
