package operations;
import packFunctions.TabulatedFunction;
import packFunctions.Point;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.TabulatedFunctionFactory;
import exceptions.InconsistentFunctionsException;

import java.util.function.BinaryOperator;

public class TabulatedFunctionOperationService {

    TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) throws InconsistentFunctionsException {
        checkConsistency(a, b);

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        int size = pointsA.length;

        double[] xValues = new double[size];
        double[] yValues = new double[size];

        for (int i = 0; i < size; i++) {
            if (pointsA[i].x != pointsB[i].x) {
                throw new InconsistentFunctionsException("The x values are inconsistent");
            }

            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction add(TabulatedFunction f1, TabulatedFunction f2) throws InconsistentFunctionsException {
        BiOperation addition = (u, v) -> u + v;
        return doOperation(f1, f2, addition);
    }

    public TabulatedFunction subtract(TabulatedFunction f1, TabulatedFunction f2) throws InconsistentFunctionsException {
        BiOperation subtraction = (u, v) -> u - v;
        return doOperation(f1, f2, subtraction);
    }


    private void checkConsistency(TabulatedFunction f1, TabulatedFunction f2) throws InconsistentFunctionsException {
        if (f1.getCount() != f2.getCount()) {
            throw new InconsistentFunctionsException("The functions have different numbers of points");
        }
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] point = new Point[tabulatedFunction.getCount()];
        for (Point poi : tabulatedFunction) {
            point[i++] = poi;
        }
        return point;
    }

    public TabulatedFunction multiply(TabulatedFunction f1, TabulatedFunction f2) throws InconsistentFunctionsException {
        BiOperation multiplication = (u, v) -> u * v;
        return doOperation(f1, f2, multiplication);
    }

    public TabulatedFunction divide(TabulatedFunction f1, TabulatedFunction f2) throws InconsistentFunctionsException {
        BiOperation division = (u, v) -> u / v;
        return doOperation(f1, f2, division);
    }
}
