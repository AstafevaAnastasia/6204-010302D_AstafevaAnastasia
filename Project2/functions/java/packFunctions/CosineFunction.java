// Класс CosineFunction реализует интерфейс MathFunction
package packFunctions;
public class CosineFunction implements MathFunction{
    public double apply(double x) {
        // Реализация функции y = cos(x)
        return Math.cos(x);
    }
}
