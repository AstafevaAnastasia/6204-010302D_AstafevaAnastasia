package packFunctions;
import operations.TabulatedFunctionOperationService;
import org.junit.Assert;
import org.junit.Test;

public class TabulatedFunctionOperationTest {

    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {4.0, 5.0, 6.0};
    TabulatedFunction tabFunc = new ArrayTabulatedFunction(xValues, yValues);
    TabulatedFunctionOperationService tabFuncServ;

    @Test
    public void testAsPoints() {
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(tabFunc);
        int i = 0;
        for (Point poi: arrayOfPoints) {
            Assert.assertEquals(poi.x, tabFunc.getX(i), 0.0001);
            Assert.assertEquals(poi.y, tabFunc.getY(i++), 0.0001);
        }
    }
}
