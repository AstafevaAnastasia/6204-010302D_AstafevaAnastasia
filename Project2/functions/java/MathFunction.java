interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        CompositeFunction compFunc = new CompositeFunction(afterFunction, this);
        return compFunc;
    }

}