package packFunctions;
public interface MathFunction {
    double apply(double x);

    // метод с реализацией по умолчанию andThen (для сложных-сложных функций)
    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(afterFunction, this);
    }

}