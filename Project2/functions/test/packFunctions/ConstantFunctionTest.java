package packFunctions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ConstantFunctionTest {
    private final ConstantFunction ConstantFunction = new ConstantFunction(5);
    private final ZeroFunction ZeroFunction = new ZeroFunction();
    private final UnitFunction UnitFunction = new UnitFunction();
    @Test
    public void testApply(){
        assertEquals(0.0, ZeroFunction.apply(2.0), 0.000001);
        assertEquals(5.0, ConstantFunction.apply(2.0), 0.000001);
        assertEquals(5.0, ConstantFunction.apply(111.0), 0.000001);
        assertEquals(1.0, UnitFunction.apply(111.0), 0.000001);
    }
}