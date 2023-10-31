package packFunctions;
import packFunctions.AbstractTabulatedFunction;
import static org.junit.Assert.*;
import org.junit.Test;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

public class MockTabulatedFunctionTest {

    private static final double EPSILON = 1e-10;
    @Test
    public void testCheckLength(){
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        try {
            AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
            System.out.println("The lengths of the arrays are the same");
        } catch (DifferentLengthOfArraysException e) {
            System.out.println(e.getMessage());
        }

        double[] xValues2 = {1.0, 2.0, 3.0};
        double[] yValues2 = {4.0, 5.0};

        try {
            AbstractTabulatedFunction.checkLengthIsTheSame(xValues2, yValues2);
            System.out.println("The lengths of the arrays are the same");
        } catch (DifferentLengthOfArraysException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void checkIsSorted() {
        double[] x = {1.0, 2.0, 3.0};
        try {
            AbstractTabulatedFunction.checkIsSorted(x);
            System.out.println("Array is sorted");
        }
        catch (ArrayIsNotSortedException e) {
            System.out.println(e.getMessage());
        }
        double[] x2 = {3.0, 2.0, 3.0};
        try {
            AbstractTabulatedFunction.checkIsSorted(x2);
            System.out.println("Array is sorted");
        }
        catch (ArrayIsNotSortedException e) {
            System.out.println(e.getMessage());
        }
    }
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
        assertEquals(0.0, function.apply(0.0), EPSILON);
        assertEquals(-1.0, function.apply(-2.0), EPSILON);
        assertEquals(1.0, function.apply(2.0), EPSILON);
    }

}