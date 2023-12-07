package packFunctions.factory;

import packFunctions.LinkedListTabulatedFunction;
import packFunctions.TabulatedFunction;
import packFunctions.*;
import java.util.LinkedList;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
    public TabulatedFunction createTabulatedFunction(MathFunction function, double leftX, double rightX, int pointsCount) {

        return new LinkedListTabulatedFunction(function, leftX, rightX, pointsCount);
    }
}
