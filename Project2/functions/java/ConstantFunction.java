public class ConstantFunction implements MathFunction {
    private final double constantNum;

    public ConstantFunction(double constNum) {
        this.constantNum = constNum;
    }

    @Override
    public double apply(double x) {
        return constantNum;
    }

    public double getConstantNum() {
        return constantNum;
    }
}
// Класс ZeroFunction
class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0);
    }
}

// Класс UnitFunction
class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1);
    }
}
