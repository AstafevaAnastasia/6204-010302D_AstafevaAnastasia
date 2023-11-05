package packFunctions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StrictTabulatedFunctionTest {

    @Test
    public void testStrictArray() {
        TabulatedFunction array = new ArrayTabulatedFunction(new double[]{0, 1, 2}, new double[]{0, 1, 4});
        TabulatedFunction strictArray = new StrictTabulatedFunction(array);
        // Проверяем, что при вызове метода apply() будет брошено исключение UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> strictArray.apply(0.5));
        // Проверяем, что значение y-координаты точки с индексом 1 равно 1
        assertEquals(strictArray.getY(1), 1, 0);
    }

    @Test
    public void testStrictLinkedList() {
        TabulatedFunction list = new LinkedListTabulatedFunction(new double[]{0, 1, 2}, new double[]{0, 1, 4});
        TabulatedFunction strictList = new StrictTabulatedFunction(list);
        // Проверяем, что при вызове метода apply() будет брошено исключение UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> strictList.apply(0.5));
        // Проверяем, что значение y-координаты точки с индексом 1 равно 1
        assertEquals(strictList.getY(1), 1, 0);
    }
}