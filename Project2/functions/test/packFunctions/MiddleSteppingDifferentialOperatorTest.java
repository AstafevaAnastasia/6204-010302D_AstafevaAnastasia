package packFunctions;

import org.junit.Test;
import operations.MiddleSteppingDifferentialOperator;
import packFunctions.MathFunction.*;
import static org.junit.Assert.*;
public class MiddleSteppingDifferentialOperatorTest {

    SqrFunction f = new SqrFunction();

    MathFunction f1 = new CosineFunction();
    MiddleSteppingDifferentialOperator middleOperation = new MiddleSteppingDifferentialOperator(5);


    @Test
    public void testDerive(){

        MathFunction derivedSqr = middleOperation.derive(f);
        MathFunction derivedCos = middleOperation.derive(f1);

        assertEquals(50.0, derivedSqr.apply(1), 0.0001);
        assertEquals(-1.3396915197002224, derivedCos.apply(6), 0.0001);
    }
}
