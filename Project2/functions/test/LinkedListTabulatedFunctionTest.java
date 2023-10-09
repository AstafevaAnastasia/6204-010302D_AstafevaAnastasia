import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LinkedListTabulatedFunctionTest {

    private final double[] xValues = {0.0, 1.0, 2.0, 3.0, 4.0};
    private final double[] yValues = {0.0, 1.0, 4.0, 9.0, 16.0};
    private TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);



    @Test
    public void testGetCount() {
        assertEquals(5, function.getCount(), 0.0001);
    }

    @Test
    public void testGetX() {
        assertEquals(0.0, function.getX(0), 0.0001);
        assertEquals(1.0, function.getX(1), 0.0001);
        assertEquals(2.0, function.getX(2), 0.0001);
        assertEquals(3.0, function.getX(3), 0.0001);
        assertEquals(4.0, function.getX(4), 0.0001);
    }

    @Test
    public void testGetY() {
        assertEquals(0.0, function.getY(0), 0.0001);
        assertEquals(1.0, function.getY(1), 0.0001);
        assertEquals(4.0, function.getY(2), 0.0001);
        assertEquals(9.0, function.getY(3), 0.0001);
        assertEquals(16.0, function.getY(4), 0.0001);
    }

    @Test
    public void testSetY() {
        function.setY(2, 5.0);
        assertEquals(5.0, function.getY(2), 0.0001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(2, function.indexOfX(2.0), 0.0001);
        assertEquals(-1, function.indexOfX(2.5), 0.0001);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(3, function.indexOfY(9.0), 0.0001);
        assertEquals(-1, function.indexOfY(12.0), 0.0001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(0.0, function.leftBound(), 0.0001);
    }

    @Test
    public void testRightBound() {
        assertEquals(4.0, function.rightBound(), 0.0001);
    }

    @Test
    public void testApply() {
        assertEquals(4.0, function.apply(2.0), 0.0001);
        assertEquals(2.5, function.apply(1.5), 0.0001);
        assertEquals(-1.0, function.apply(-1.0), 0.0001);
    }

    /* @Test
    public void testInterpolate() {
        assertEquals(2.5, ((LinkedListTabulatedFunction) function).interpolate(1.5, 1), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> ((LinkedListTabulatedFunction) function).interpolate(1.5, 4));
    } */

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction.Node floorNode = ((LinkedListTabulatedFunction) function).floorNodeOfX(1.5);
        assertEquals(2.5, ((LinkedListTabulatedFunction) function).interpolate(1.5, floorNode), 0.0001);
        assertEquals(4.0, ((LinkedListTabulatedFunction) function).interpolate(2.0, floorNode), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> ((LinkedListTabulatedFunction) function).interpolate(1.5, null));
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(-1.0, ((LinkedListTabulatedFunction) function).extrapolateLeft(-1.0), 0.0001);
        assertEquals(0.0, ((LinkedListTabulatedFunction) function).extrapolateLeft(0.0), 0.0001);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(30.0, ((LinkedListTabulatedFunction) function).extrapolateRight(6.0), 0.0001);
        assertEquals(16.0, ((LinkedListTabulatedFunction) function).extrapolateRight(4.0), 0.0001);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(1, ((LinkedListTabulatedFunction) function).floorIndexOfX(1.5), 0.0001);
        assertEquals(-1, ((LinkedListTabulatedFunction) function).floorIndexOfX(-1.0), 0.0001);
        assertEquals(3, ((LinkedListTabulatedFunction) function).floorIndexOfX(3.5), 0.0001);
        assertEquals(3, ((LinkedListTabulatedFunction) function).floorIndexOfX(4.0), 0.0001);
    }

    @Test
    public void testRemove(){
        ((LinkedListTabulatedFunction) function).remove(0);
        assertEquals(4, function.getCount());
        assertEquals(1.0, function.getX(0), 0.0001);
        assertEquals(1.0, function.getY(0), 0.0001);
        ((LinkedListTabulatedFunction) function).remove(2);
        assertEquals(3, function.getCount());
        assertEquals(4.0, function.getX(2), 0.0001);
        assertEquals(16.0, function.getY(2), 0.0001);
    }
}