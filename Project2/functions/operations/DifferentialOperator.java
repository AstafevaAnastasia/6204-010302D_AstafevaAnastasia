package operations;

import packFunctions.MathFunction;

public interface DifferentialOperator<T extends MathFunction>{
    T derive(T function);
}