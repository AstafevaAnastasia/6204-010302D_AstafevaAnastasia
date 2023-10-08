// класс константных функций, реализующий интерфейс математических функций
public class ConstantFunction implements MathFunction {
    private final double constantNum;

    public ConstantFunction(double constNum) {
        this.constantNum = constNum;
    }

    @Override
    public double apply(double x) {
        return constantNum;
    } // возвращает всегда одно и то же число

    public double getConstantNum() {
        return constantNum;
    } // геттер
}
// Класс ZeroFunction
class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0);
    } // вызываем конструктор суперкласса со значением 0, реализуя тем самым класс функций, возвращающих ноль
}

// Класс UnitFunction
class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1);
    } // вызываем конструктор суперкласса со значением 1
}
