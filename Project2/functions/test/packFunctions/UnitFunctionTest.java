package packFunctions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UnitFunctionTest {
    private final UnitFunction UnitFunction = new UnitFunction();

    @Test
    public void testApply(){
        assertEquals(1.0, UnitFunction.apply(111.0), 0.000001);
    }
}
