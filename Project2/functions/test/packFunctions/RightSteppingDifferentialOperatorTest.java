package packFunctions;

import org.junit.Test;
import operations.RightSteppingDifferentialOperator;
import packFunctions.MathFunction.*;
import static org.junit.Assert.*;
public class RightSteppingDifferentialOperatorTest {

    SqrFunction f = new SqrFunction();

    MathFunction f1 = new CosineFunction();
    RightSteppingDifferentialOperator rightOperation = new RightSteppingDifferentialOperator(5);


    @Test
    public void testDerive(){

        MathFunction derivedSqr = rightOperation.derive(f);
        MathFunction derivedCos = rightOperation.derive(f1);

        assertEquals(7.0, derivedSqr.apply(1), 0.0001);
        assertEquals(-0.19114891773246304, derivedCos.apply(6), 0.0001);
    }
}