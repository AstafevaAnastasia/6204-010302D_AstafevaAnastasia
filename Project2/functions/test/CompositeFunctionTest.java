import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CompositeFunctionTest {

    @Test
    public void testCompositeFunction() {
        // Создаем сложные функции
        MathFunction f = new CosineFunction();
        MathFunction g = new CosTwoArgFunction();

        // Создаем сложную функцию h(x) = g(f(x))
        CompositeFunction h = new CompositeFunction(f,g);

        // Тестируем применение сложной функции
        assertEquals(-0.1833, h.apply(0.5), 0.0001);
        // В этом тесте мы ожидаем, что результат h(0.5) будет приближенно равен cos(cos(0.5) + cos(0.5))
    }
}