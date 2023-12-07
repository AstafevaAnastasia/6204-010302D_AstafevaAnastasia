package packFunctions.factory;

import packFunctions.ArrayTabulatedFunction;
import packFunctions.TabulatedFunction;
import packFunctions.*;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    public TabulatedFunction createTabulatedFunction(MathFunction function, double leftX, double rightX, int pointsCount) {
        double step = (rightX - leftX) / (pointsCount - 1);
        double[] xValues = new double[pointsCount];
        double[] yValues = new double[pointsCount];

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            double y = function.apply(x);
            xValues[i] = x;
            yValues[i] = y;
        }

        return new ArrayTabulatedFunction(xValues, yValues);
    }

}
