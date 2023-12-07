package packFunctions.factory;

import packFunctions.MathFunction;
import packFunctions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction createTabulatedFunction(MathFunction source, double x, double x2, int count);
}