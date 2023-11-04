package packFunctions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThrows;

public class ArrayTabulatedFunctionTest {

    private Point[] ArrayTabulatedFunction;

    @Test
    public void testConstructorWithArrays() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        Assert.assertEquals(3, function.getCount());
        Assert.assertEquals(1.0, function.getX(0), 0.0);
        Assert.assertEquals(4.0, function.getY(0), 0.0);
    }

    @Test
    public void tesCheckIsSortedexception() {
        double[] xValues = {1.0, 2.5, 0.5};
        double[] yValues = {1.0, 2.0, 3.0};
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    public void testDifferentArrayLengthsException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0, 7.0};

        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    public void testInterpolateOutsideInterval() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Assert.assertThrows(InterpolationException.class, () -> function.interpolate(0.5, 1));
        Assert.assertThrows(InterpolationException.class, () -> function.interpolate(3.5, 0));
    }

    @Test
    public void testSecondConstructor() {
        MathFunction f = new ConstantFunction(5);
        int startX = 0, endX = 2;
        int count = 3;
        ArrayTabulatedFunction f2 = new ArrayTabulatedFunction(f, startX, endX, count);
        Assert.assertEquals(startX, f2.leftBound(), 0.01);
        Assert.assertEquals(endX, f2.rightBound(), 0.01);
        Assert.assertEquals(count, f2.getCount());

    }

    @Test
    public void testEqualsTF() {
        ArrayTabulatedFunction function = createFunction();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        double[] xV = {1.0, 2.0, 7.5};
        double[] yV = {4.0, 5.0, 6.0};
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction f2 = new LinkedListTabulatedFunction(xV, yV);
        Assert.assertTrue(function.equalsTF(f));
        Assert.assertFalse(function.equalsTF(f2));
        Assert.assertFalse(function.equalsTF(null));
    }

    @Test
    public void testGetX() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(2.0, function.getX(1), 0.0);
        assertThrows(IllegalArgumentException.class, () -> function.getX(-1));
        assertThrows(IllegalArgumentException.class, () -> function.getX(10));
    }

    @Test
    public void testGetY() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(5.0, function.getY(1), 0.0);
        assertThrows(IllegalArgumentException.class, () -> function.getY(-1));
        assertThrows(IllegalArgumentException.class, () -> function.getY(10));
    }

    @Test
    public void testSetY() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        function.setY(1, 10.0);
        Assert.assertEquals(10.0, function.getY(1), 0.0);
        assertThrows(IllegalArgumentException.class, () -> function.setY(-1, 5.0));
        assertThrows(IllegalArgumentException.class, () -> function.setY(10, 5.0));
    }

    @Test
    public void testIndexOfX() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(1, function.indexOfX(2.0));
    }

    @Test
    public void testIndexOfY() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(1, function.indexOfY(5.0));
    }

    @Test
    public void testLeftBound() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(1.0, function.leftBound(), 0.0);
    }

    @Test
    public void testRightBound() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(3.0, function.rightBound(), 0.0);
    }

    @Test
    public void testInterpolate() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(4.5, function.interpolate(1.5, 0), 0.0);
    }

    @Test
    public void testFloorIndexOfX() {
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(1, function.floorIndexOfX(2.5));
        assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(0));
    }

    @Test
    public void testExtrapolateLeft() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(3.5, function.extrapolateLeft(0.5), 0.0);
    }

    @Test
    public void testExtrapolateRight() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(6.5, function.extrapolateRight(3.5), 0.0);
    }

    @Test
    public void testInterpolateSingle() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(5.5, function.interpolate(2.5), 0.0);
    }

    private ArrayTabulatedFunction createFunction() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testToString() {
        ArrayTabulatedFunction f = createFunction();
        Assert.assertEquals("[ 1.0, 4.0 ] [ 2.0, 5.0 ] [ 3.0, 6.0 ] ", f.toString());
    }

    @Test
    public void testEquals() {
        ArrayTabulatedFunction f = createFunction();
        ArrayTabulatedFunction f2 = createFunction();
        IdentityFunction r = new IdentityFunction();
        Assert.assertTrue(f.equals(f2));
        Assert.assertFalse(f.equals(r));
    }

    @Test
    public void testHashCode() {
        ArrayTabulatedFunction f = createFunction();
        ArrayTabulatedFunction f2 = createFunction();
        Assert.assertEquals(f.hashCode(), f2.hashCode());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        ArrayTabulatedFunction f = createFunction();
        ArrayTabulatedFunction f2 = f.clone();
        Assert.assertEquals(f, f2);
    }

    //Покрытие метода insert тестами
    @Test
    public void testInsert() {
        ArrayTabulatedFunction function = createFunction();
        function.insert(2.5, 5.5);
        Assert.assertEquals(4, function.getCount()); //проверяем, что кол-во элементов увеличилось на 1
        Assert.assertEquals(2.5, function.getX(2), 0.0001);
        Assert.assertEquals(5.5, function.getY(2), 0.0001);
    }

    @Test
    public void testIterator() {
        ArrayTabulatedFunction function = createFunction();
        Iterator<Point> iterator = function.iterator();
        int i = 0, j = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assert.assertEquals(point.getX(), function.getX(i), 0.1);
            Assert.assertEquals(point.getY(), function.getY(i++), 0.1);
        }
        for (Point point : function) {
            Assert.assertEquals(point.getX(), function.getX(j), 0.1);
            Assert.assertEquals(point.getY(), function.getY(j++), 0.1);
        }
    }
}