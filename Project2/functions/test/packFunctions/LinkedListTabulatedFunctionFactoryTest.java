package packFunctions;

import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class LinkedListTabulatedFunctionFactoryTest {
    double[] xValue = {0, 1, 2, 3, 4, 5};
    double[] yValue = {0, 1, 4, 9, 16, 25};
    LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValue, yValue);

    @Test
    public void testCreate() {
        LinkedListTabulatedFunctionFactory function2 = new LinkedListTabulatedFunctionFactory();
        ArrayTabulatedFunctionFactory functionList = new ArrayTabulatedFunctionFactory();
        double[] xValueF = {1, 2, 3, 4, 5, 6};
        double[] yValueF = {0, 1, 2, 3, 4, 5};
        Assert.assertTrue(function.getClass() == (function2.create(xValueF, yValueF)).getClass());
        Assert.assertFalse(function.getClass() == (functionList.create(xValueF, yValueF)).getClass());
    }
}
