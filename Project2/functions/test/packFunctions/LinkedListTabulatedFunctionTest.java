package packFunctions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunctionTest {

    private final double[] xValues = {0.0, 1.0, 2.0, 3.0, 4.0};
    private final double[] yValues = {0.0, 1.0, 4.0, 9.0, 16.0};
    private TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

    @Test
    public void testLinkedListTabulatedFunction() {
        MathFunction mathFunction = new CosineFunction();
        double startX = 0;
        double endX = Math.PI;
        int pointsCount = 5;
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(mathFunction, startX, endX, pointsCount);
        assertEquals(startX, tabulatedFunction.leftBound(), 0.01);
        assertEquals(endX, tabulatedFunction.rightBound(), 0.01);
        assertEquals(pointsCount, tabulatedFunction.getCount());
        assertEquals(3*Math.PI/4, tabulatedFunction.getX(3), 0.01);
    }

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
        assertThrows(IllegalArgumentException.class, () -> function.getX(-1));
        assertThrows(IllegalArgumentException.class, () -> function.getX(10));
    }

    @Test
    public void testGetY() {
        assertEquals(0.0, function.getY(0), 0.0001);
        assertEquals(1.0, function.getY(1), 0.0001);
        assertEquals(4.0, function.getY(2), 0.0001);
        assertEquals(9.0, function.getY(3), 0.0001);
        assertEquals(16.0, function.getY(4), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> function.getY(-1));
        assertThrows(IllegalArgumentException.class, () -> function.getY(10));
    }

    @Test
    public void testSetY() {
        function.setY(2, 5.0);
        assertEquals(5.0, function.getY(2), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> function.setY(-1, 5.0));
        assertThrows(IllegalArgumentException.class, () -> function.setY(10, 5.0));
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

    @Test
    public void testInterpolate() {
        int floorIndex = ((LinkedListTabulatedFunction) function).floorIndexOfX(1.5);
        assertEquals(2.5, ((LinkedListTabulatedFunction) function).interpolate(1.5, floorIndex), 0.0001);
        assertEquals(4.0, ((LinkedListTabulatedFunction) function).interpolate(2.0, floorIndex), 0.0001);
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
        assertThrows(IllegalArgumentException.class, () -> ((LinkedListTabulatedFunction) function).floorIndexOfX(-1.0));
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
        assertThrows(IllegalArgumentException.class, () -> ((LinkedListTabulatedFunction) function).remove(-1));
        assertEquals(3, function.getCount());
        assertEquals(4.0, function.getX(2), 0.0001);
        assertEquals(16.0, function.getY(2), 0.0001);
    }

    // Метод toString для узла тестируется через toString для списка, т.к. он там используется
    @Test
    public void testToString() {
        String expected = "(0.0; 0.0), (1.0; 1.0), (2.0; 4.0), (3.0; 9.0), (4.0; 16.0)";
        assertEquals(expected, function.toString());
    }

    @Test
    public void testHashCode() {
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(-5.0, 1.0);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(0.05, 7.1);
        LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(-5.0, 1.0);

        assertEquals(node1.hashCode(), node3.hashCode());
        assertNotEquals(10, node2.hashCode());
        assertNotEquals(node2.hashCode(), node1.hashCode());
    }

    @Test
    public void testEquals() {
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(0.00003, -55.9);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(0.00003, -55.9);
        assertTrue(node1.equals(node2));
    }

    @Test
    public void testClone() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(-3.4, 33.4);
        Object copy = node.clone();
        assertTrue(node.equals(copy));
    }

    @Test
    public void testHashCodeLinkedList() {
        LinkedListTabulatedFunction functionCopy = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(function.hashCode(), functionCopy.hashCode());
        assertNotEquals(10, function.hashCode());
    }

    @Test
    public void testEqualsLinkedList() {
        LinkedListTabulatedFunction functionCopy = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction functionNotCopy = new LinkedListTabulatedFunction(new double[] {-5.0, 1.5, 2.33}, new double[] {-2.33, -1.5, 5.0});
        assertTrue(function.equals(functionCopy));
        assertFalse(function.equals(functionNotCopy));
        assertFalse(function.equals(null));
    }

    @Test
    public void testCloneLinkedList() {
        assertEquals(function, ((LinkedListTabulatedFunction)function).clone());
    }

    @Test
    public void tesCheckIsSortedexception() {
        double[] xValues = {1.0, 2.5, 0.5};
        double[] yValues = {1.0, 2.0, 3.0};
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
    }
    @Test
    public void testDifferentArrayLengthsException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0, 7.0};

        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
    }

    @Test
    public void testInterpolateOutsideInterval() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0};
        double[] yValues = {4.0, 5.0, 6.0, 7.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(-1, 1);
        });
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(3.5, 1);
        });
    }

    @Test
    public void testIteratorWhile() {
        Iterator<Point> iterator = ((LinkedListTabulatedFunction)function).iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, i, 0);
            assertEquals(point.y, i * i, 0);
            i++;
        }
    }

    @Test
    public void testIteratorForEach() {
        LinkedListTabulatedFunction functionNew = new LinkedListTabulatedFunction(xValues, yValues);
        int i = 0;
        for (Object point : functionNew) {
            Point p = (Point) point;
            assertEquals(p.x, i, 0);
            assertEquals(p.y, i * i, 0);
            i++;
        }
    }

}