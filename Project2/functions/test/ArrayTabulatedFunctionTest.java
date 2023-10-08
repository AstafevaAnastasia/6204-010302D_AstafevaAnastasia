import org.junit.Assert;
import org.junit.Test;

public class ArrayTabulatedFunctionTest {

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
    public void testGetX() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(2.0, function.getX(1), 0.0);
    }

    @Test
    public void testGetY() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(5.0, function.getY(1), 0.0);
    }

    @Test
    public void testSetY() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        function.setY(1, 10.0);
        Assert.assertEquals(10.0, function.getY(1), 0.0);
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
        Assert.assertEquals(5.5, function.interpolate(1.5, 1), 0.0);
    }

    @Test
    public void testFloorIndexOfX() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(1, function.floorIndexOfX(2.5));
    }

    @Test
    public void testExtrapolateLeft() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(2.5, function.extrapolateLeft(0.5), 0.0);
    }

    @Test
    public void testExtrapolateRight() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(7.0, function.extrapolateRight(3.5), 0.0);
    }

    @Test
    public void testInterpolateSingle() {
        // Assuming the function is already created
        ArrayTabulatedFunction function = createFunction();
        Assert.assertEquals(6.5, function.interpolate(2.5), 0.0);
    }

    private ArrayTabulatedFunction createFunction() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        return new ArrayTabulatedFunction(xValues, yValues);
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
}