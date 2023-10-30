package packFunctions;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class ArrayTabulatedFunctionFactoryTest {
    double[] xValue = {0, 1, 2, 3, 4, 5};
    double[] yValue = {0, 1, 4, 9, 16, 25};
    ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValue, yValue);

    @Test
    public void testCreate() {
        ArrayTabulatedFunctionFactory function2 = new ArrayTabulatedFunctionFactory();
        LinkedListTabulatedFunctionFactory functionArr = new LinkedListTabulatedFunctionFactory();
        double[] xValueF = {1, 2, 3, 4, 5, 6};
        double[] yValueF = {0, 1, 2, 3, 4, 5};
        Assert.assertTrue(function.getClass() == (function2.create(xValueF, yValueF)).getClass());
        Assert.assertFalse(function.getClass() == (functionArr.create(xValueF, yValueF)).getClass());
    }
}
