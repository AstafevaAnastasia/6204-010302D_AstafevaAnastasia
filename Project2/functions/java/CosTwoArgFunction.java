// класс для функции y=cos2x
public class CosTwoArgFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.cos((x+x));
    }
}
