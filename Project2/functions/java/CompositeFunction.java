// Класс CompositeFunction реализует интерфейс MathFunction
public class CompositeFunction implements MathFunction {
    // Объявляем две переменные типа MathFunction
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    // Конструктор принимает два аргумента типа MathFunction и инициализирует поля класса
    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    // Реализуем метод apply интерфейса MathFunction
    @Override
    public double apply(double x) {
        // Сначала применяем первую функцию к аргументу x
        double intermediateResult = firstFunction.apply(x);
        // Затем применяем вторую функцию к результату первой
        return secondFunction.apply(intermediateResult);
    }
}