package operations;

import packFunctions.*;
import packFunctions.factory.TabulatedFunctionFactory;
import packFunctions.factory.ArrayTabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    protected TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] array = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[function.getCount()];
        double[] yValues = new double[function.getCount()];

        int i = 0;
        while (i < (xValues.length - 1)) {
            xValues[i] = array[i].x;
            yValues[i] = (array[i + 1].y - array[i].y) / (array[i + 1].x - array[i].x);
            ++i;
        }
        xValues[i] = array[i].x;
        yValues[i] = yValues[i - 1];
        return factory.create(xValues, yValues);
    }
}