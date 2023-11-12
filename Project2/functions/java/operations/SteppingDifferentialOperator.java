package operations;

import packFunctions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction > {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isNaN(step) || Double.isInfinite(step)) {
            throw new IllegalArgumentException();
        }
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (step <= 0 || Double.isNaN(step) || Double.isInfinite(step)) {
            throw new IllegalArgumentException();
        }
        this.step = step;
    }

    @Override
    public abstract MathFunction derive(MathFunction function);


}
