package packFunctions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ZeroFunctionTest {

    private final ZeroFunction ZeroFunction = new ZeroFunction();
    @Test
    public void testApply(){
        assertEquals(0.0, ZeroFunction.apply(2.0), 0.000001);
    }
}
