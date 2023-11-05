package packFunctions;

import org.junit.Test;
import operations.TabulatedDifferentialOperator;
import packFunctions.factory.*;
import static org.junit.Assert.*;

public class TabulatedDifferentialOperatorTest {

    // Создание табулированной функции с известной производной
    double[] xValues = {0, 1, 2, 3, 4};
    double[] yValues = {0, 1, 4, 9, 16};
    double[] dydxValues = {1, 3, 5, 7, 7}; // значения производной

    @Test
    public void testSetFactory() {
        // Тестирование оператора с использованием ArrayTabulatedFunctionFactory
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
        // Проверка соответствия фабрики оператора фабрике, переданной в конструктор
        assertTrue(operator.getFactory().getClass() == factory.getClass());

        // Тестирование оператора с использованием LinkedListTabulatedFunctionFactory
        factory = new LinkedListTabulatedFunctionFactory();
        operator.setFactory(factory);
        // Проверка соответствия фабрики оператора новой фабрике
        assertTrue(operator.getFactory().getClass() == factory.getClass());
    }

    @Test
    public void testDerive() {
        LinkedListTabulatedFunctionFactory f = new LinkedListTabulatedFunctionFactory();
        // Создание объекта TabulatedDifferentialOperator с использованием созданной фабрики
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(f);
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        // Получение производной функции
        TabulatedFunction df = operation.derive(function);
        assertEquals(dydxValues[0], df.getY(0), 0.0);
        assertEquals(dydxValues[1], df.getY(1), 0.0);
        assertEquals(dydxValues[2], df.getY(2), 0.0);
        assertEquals(dydxValues[3], df.getY(3), 0.0);
        assertEquals(dydxValues[4], df.getY(3), 0.0);
    }
}