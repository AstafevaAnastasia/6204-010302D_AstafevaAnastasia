
interface MathFunction {
    double apply(double x);

    // метод с реализацией по умолчанию andThen (для сложных-сложных функций)
    default CompositeFunction andThen(MathFunction afterFunction) {
        CompositeFunction compFunc = new CompositeFunction(afterFunction, this);
        return compFunc;
    }

}