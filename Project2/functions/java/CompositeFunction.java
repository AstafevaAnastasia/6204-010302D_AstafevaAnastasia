public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    @Override
    public double apply(double x) {
        // Сначала применяем первую функцию, а затем вторую к результату первой
        double intermediateResult = firstFunction.apply(x);
        return secondFunction.apply(intermediateResult);
    }
}