// класс константных функций, реализующий интерфейс математических функций
package packFunctions;
public class ConstantFunction implements MathFunction {
    private final double constantNum;

    public ConstantFunction(double constNum) {
        this.constantNum = constNum;
    }

    public double apply(double x) {
        return constantNum;
    } // возвращает всегда одно и то же число

    public double getConstantNum() {
        return constantNum;
    } // геттер
}

