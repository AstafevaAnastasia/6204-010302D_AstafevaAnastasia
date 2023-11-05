package packFunctions;

import org.junit.Test;
import operations.LeftSteppingDifferentialOperator;
import packFunctions.MathFunction.*;
import static org.junit.Assert.*;
public class LestSteppingDifferentialOperatorTest {

    SqrFunction f = new SqrFunction();

    MathFunction f1 = new CosineFunction();


    @Test
    public void testDerive(){

        LeftSteppingDifferentialOperator leftOperation = new LeftSteppingDifferentialOperator(5);
        MathFunction derivedSqr = leftOperation.derive(f);
        MathFunction derivedCos = leftOperation.derive(f1);

        assertEquals(-3.0, derivedSqr.apply(1), 0.0001);
        assertEquals(0.08397359615644524, derivedCos.apply(6), 0.0001);
    }
}
