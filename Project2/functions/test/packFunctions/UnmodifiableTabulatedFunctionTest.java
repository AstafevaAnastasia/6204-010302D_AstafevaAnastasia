package packFunctions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class UnmodifiableTabulatedFunctionTest {
    double[] xValues = {0.0, 1.0, 2.0};
    double[] yValues = {0.0, 1.0, 4.0};
    TabulatedFunction originalArrayFunction = new ArrayTabulatedFunction(xValues, yValues);
    TabulatedFunction originalListFunction = new LinkedListTabulatedFunction(xValues, yValues);
    TabulatedFunction unmodifiableArrayFunction = new UnmodifiableTabulatedFunction(originalArrayFunction);
    TabulatedFunction unmodifiableListFunction = new UnmodifiableTabulatedFunction(originalListFunction);

    @Test
    public void testException() {
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableArrayFunction.setY(2, 1.5));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableListFunction.setY(2, 1.5));
    }

    @Test
    public void testClass() {
        assertEquals(0, unmodifiableArrayFunction.getY(0), 0.00001);
        assertEquals(0, unmodifiableListFunction.getY(0), 0.00001);
        assertEquals(1, unmodifiableArrayFunction.getX(1), 0.00001);
        assertEquals(1, unmodifiableListFunction.getY(1), 0.00001);
        assertEquals(3, unmodifiableArrayFunction.getCount(), 0.00001);
        assertEquals(3, unmodifiableListFunction.getCount(), 0.00001);
        assertEquals(0, unmodifiableArrayFunction.leftBound(), 0.00001);
        assertEquals(0, unmodifiableListFunction.leftBound(), 0.00001);
        assertEquals(2, unmodifiableArrayFunction.rightBound(), 0.00001);
        assertEquals(2, unmodifiableListFunction.rightBound(), 0.00001);
        assertEquals(0, unmodifiableArrayFunction.indexOfX(0), 0.00001);
        assertEquals(0, unmodifiableListFunction.indexOfX(0), 0.00001);
        assertEquals(0, unmodifiableArrayFunction.indexOfY(0), 0.00001);
        assertEquals(0, unmodifiableListFunction.indexOfY(0), 0.00001);
        assertEquals(4.0,  unmodifiableArrayFunction.apply(2), 0.1);
        assertEquals(2.5, unmodifiableListFunction.apply(1.5), 0.0001);

    }


}
