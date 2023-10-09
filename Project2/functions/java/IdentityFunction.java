// Класс IdentityFunction реализует интерфейс MathFunction
public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    } //возвращается тот же элемент
}