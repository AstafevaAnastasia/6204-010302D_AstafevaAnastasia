package packFunctions;
import operations.TabulatedFunctionOperationService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import packFunctions.factory.TabulatedFunctionFactory;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import exceptions.InconsistentFunctionsException;

public class TabulatedFunctionOperationTest {

    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {4.0, 5.0, 6.0};
    TabulatedFunction tabFunc = new ArrayTabulatedFunction(xValues, yValues);

    @Test
    public void testAsPoints() {
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(tabFunc);
        int i = 0;
        for (Point poi: arrayOfPoints) {
            assertEquals(poi.x, tabFunc.getX(i), 0.0001);
            assertEquals(poi.y, tabFunc.getY(i++), 0.0001);
        }
    }

    private TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
    private TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
    private TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();

    @Test
    public void testAdd_ArrayAndLinkedList() throws InconsistentFunctionsException {
        TabulatedFunction f1 = arrayFactory.create(new double[]{1, 2, 3}, new double[]{2, 4, 6});
        TabulatedFunction f3 = linkedListFactory.create(new double[]{1, 2, 3}, new double[]{3, 5, 7});
        TabulatedFunction f2 = arrayFactory.create(new double[]{1, 2, 3}, new double[]{2, 3, 4});
        TabulatedFunction f4 = linkedListFactory.create(new double[]{1, 2, 3}, new double[]{1, 1, 1});

        TabulatedFunction result = operationService.add(f1, f2);;
        TabulatedFunction result1 = operationService.add(f1, f3);
        TabulatedFunction result2 = operationService.add(f4, f2);

        assertNotNull(result);
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(3, result.getCount());
        assertEquals(3, result1.getCount());
        assertEquals(3, result2.getCount());
        assertEquals(1, result.getX(0), 0);
        assertEquals(1, result1.getX(0), 0);
        assertEquals(1, result2.getX(0), 0);
        assertEquals(4, result.getY(0), 0);
        assertEquals(5, result1.getY(0), 0);
        assertEquals(3, result2.getY(0), 0);
        assertEquals(2, result.getX(1), 0);
        assertEquals(2, result1.getX(1), 0);
        assertEquals(2, result2.getX(1), 0);
        assertEquals(7, result.getY(1), 0);
        assertEquals(9, result1.getY(1), 0);
        assertEquals(4, result2.getY(1), 0);
        assertEquals(3, result.getX(2), 0);
        assertEquals(3, result1.getX(2), 0);
        assertEquals(3, result2.getX(2), 0);
        assertEquals(10, result.getY(2), 0);
        assertEquals(13, result1.getY(2), 0);
        assertEquals(5, result2.getY(2), 0);
    }

    @Test
    public void testSubtract_LinkedListAndArray() throws InconsistentFunctionsException {
        TabulatedFunction f1 = linkedListFactory.create(new double[]{1, 2, 3}, new double[]{2, 4, 6});
        TabulatedFunction f2 = arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 1, 1});
        TabulatedFunction f3 = linkedListFactory.create(new double[]{1, 2, 3}, new double[]{3, 5, 7});
        TabulatedFunction f4 = arrayFactory.create(new double[]{1, 2, 3}, new double[]{2, 3, 4});

        TabulatedFunction result = operationService.subtract(f1, f2);
        TabulatedFunction result1 = operationService.subtract(f1, f3);
        TabulatedFunction result2 = operationService.subtract(f4, f2);

        assertNotNull(result);
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(3, result.getCount());
        assertEquals(3, result1.getCount());
        assertEquals(3, result2.getCount());
        assertEquals(1, result.getX(0), 0);
        assertEquals(1, result1.getX(0), 0);
        assertEquals(1, result2.getX(0), 0);
        assertEquals(1, result.getY(0), 0);
        assertEquals(-1, result1.getY(0), 0);
        assertEquals(1, result2.getY(0), 0);
        assertEquals(2, result.getX(1), 0);
        assertEquals(2, result1.getX(1), 0);
        assertEquals(2, result2.getX(1), 0);
        assertEquals(3, result.getY(1), 0);
        assertEquals(-1, result1.getY(1), 0);
        assertEquals(2, result2.getY(1), 0);
        assertEquals(3, result.getX(2), 0);
        assertEquals(3, result1.getX(2), 0);
        assertEquals(3, result2.getX(2), 0);
        assertEquals(5, result.getY(2), 0);
        assertEquals(-1, result1.getY(2), 0);
        assertEquals(3, result2.getY(2), 0);
    }
}
