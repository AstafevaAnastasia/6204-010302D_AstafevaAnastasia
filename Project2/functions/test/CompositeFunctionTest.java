import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CompositeFunctionTest {

    // Задаем значения аргумента и функции для cosx и cos2x
    double[] xValuesCos = {0.0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2, 2*Math.PI/3, 3*Math.PI/4, 5*Math.PI/6, Math.PI};
    double[] yValuesCos = {1.0, 0.866025, 0.707107, 0.5, 0, -0.5, -0.707107, -0.866025, -1.0};
    double[] xValuesCos2 = {0.0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2, 2*Math.PI/3, 3*Math.PI/4, 5*Math.PI/6, Math.PI};
    double[] yValuesCos2 = {1.0, 0.5, 0, -0.5, -1.0, -0.5, 0, 0.5, 1.0};

    // Создаем функции
    MathFunction f = new CosineFunction();
    MathFunction g = new CosTwoArgFunction();

    // Списки
    MathFunction testFunction1 = new LinkedListTabulatedFunction(xValuesCos, yValuesCos);
    MathFunction testFunction2 = new LinkedListTabulatedFunction(xValuesCos2, yValuesCos2);
    // Массивы
    MathFunction testFunction3 = new ArrayTabulatedFunction(xValuesCos, yValuesCos);
    MathFunction testFunction4 = new ArrayTabulatedFunction(xValuesCos2, yValuesCos2);

    @Test
    public void testCompositeFunction() {

        CompositeFunction h = new CompositeFunction(f,g); // Создаем сложную функцию h(x) = g(f(x))
        assertEquals(-0.1833, h.apply(0.5), 0.0001); // Тестируем применение сложной функции
        // В этом тесте мы ожидаем, что результат h(0.5) будет приближенно равен cos(cos(0.5) + cos(0.5))

        CompositeFunction h1 = new CompositeFunction(testFunction1,testFunction2); // Создаем сложную функцию h(x) = g(f(x)) (список)
        assertEquals(1.0, h1.apply(Math.PI/2), 0.0001);
        // В этом тесте мы ожидаем, что результат h1(PI/2) будет приближенно равен cos(cos(PI/2) + cos(PI/2))

        CompositeFunction h2 = new CompositeFunction(testFunction3,testFunction4); // (массив)
        assertEquals(1.0, h2.apply(Math.PI/2), 0.0001);
    }

    @Test
    public void testIndexOfX() {
        // Тесты для списка
        assertEquals(1, ((LinkedListTabulatedFunction)testFunction1).indexOfX(Math.PI/6), 0.0001);
        assertEquals(4, ((LinkedListTabulatedFunction)testFunction2).indexOfX(Math.PI/2), 0.0001);
        //для массива
        assertEquals(1, (( ArrayTabulatedFunction)testFunction3).indexOfX(Math.PI/6), 0.0001);
        assertEquals(4, (( ArrayTabulatedFunction)testFunction4).indexOfX(Math.PI/2), 0.0001);
    }

    @Test
    public void testApply() {
        // Тесты для списка
        assertEquals(-0.416147,  ((LinkedListTabulatedFunction)testFunction1).apply( 2), 0.1);
        assertEquals(-0.653644,  ((LinkedListTabulatedFunction)testFunction2).apply(2), 0.1);
        //для массива
        assertEquals(-0.416147,  ((ArrayTabulatedFunction)testFunction3).apply( 2), 0.1);
        assertEquals(-0.653644,  ((ArrayTabulatedFunction)testFunction4).apply(2), 0.1);
    }

    @Test
    public void testInterpolate() {
        // Тесты для списка
        LinkedListTabulatedFunction.Node floorNode1 = ((LinkedListTabulatedFunction) testFunction1).floorNodeOfX(1.0);
        LinkedListTabulatedFunction.Node floorNode2 = ((LinkedListTabulatedFunction) testFunction2).floorNodeOfX(1.0);
        assertEquals(0.540302, ((LinkedListTabulatedFunction) testFunction1).interpolate(1.0, floorNode1), 0.1);
        assertEquals(-0.416147, ((LinkedListTabulatedFunction) testFunction2).interpolate(1.0, floorNode2), 0.1);
        assertThrows(IllegalArgumentException.class, () -> ((LinkedListTabulatedFunction) testFunction1).interpolate(1.5, null));
        //для массива
        assertEquals(0.540302, ((ArrayTabulatedFunction) testFunction3).interpolate(1.0, ((ArrayTabulatedFunction) testFunction3).floorIndexOfX(1.0)), 0.1);
        assertEquals(-0.416147, ((ArrayTabulatedFunction) testFunction4).interpolate(1.0, ((ArrayTabulatedFunction) testFunction4).floorIndexOfX(1.0)), 0.1);
        assertThrows(IllegalArgumentException.class, () -> ((ArrayTabulatedFunction) testFunction4).interpolate(1.5, -1));
    }

    @Test
    public void testRemove(){
        ((LinkedListTabulatedFunction) testFunction1).remove(0);
        assertEquals(8, ((LinkedListTabulatedFunction) testFunction1).getCount());
        assertEquals(Math.PI/6, ((LinkedListTabulatedFunction) testFunction1).getX(0), 0.0001);
        assertEquals(0.866025, ((LinkedListTabulatedFunction) testFunction1).getY(0), 0.0001);
        ((LinkedListTabulatedFunction) testFunction2).remove(2);
        assertEquals(8, ((LinkedListTabulatedFunction) testFunction2).getCount());
        assertEquals(Math.PI/3, ((LinkedListTabulatedFunction) testFunction2).getX(2), 0.0001);
        assertEquals(-0.5, ((LinkedListTabulatedFunction) testFunction2).getY(2), 0.0001);

        ((ArrayTabulatedFunction) testFunction3).remove(0);
        assertEquals(8, ((ArrayTabulatedFunction) testFunction3).getCount());
        assertEquals(Math.PI/6, ((ArrayTabulatedFunction) testFunction3).getX(0), 0.0001);
        assertEquals(0.866025, ((ArrayTabulatedFunction) testFunction3).getY(0), 0.0001);
        ((ArrayTabulatedFunction) testFunction4).remove(2);
        assertEquals(8, ((ArrayTabulatedFunction) testFunction4).getCount());
        assertEquals(Math.PI/3, ((ArrayTabulatedFunction) testFunction4).getX(2), 0.0001);
        assertEquals(-0.5, ((ArrayTabulatedFunction) testFunction4).getY(2), 0.0001);
    }
}