package packFunctions;
import org.junit.Test;
import org.junit.Assert;

public class CosTwoArgFunctionTest {
    private final CosTwoArgFunction cosTwoArgFunction = new CosTwoArgFunction();

    @Test
    public void testApply() {
        Assert.assertEquals(1.0, cosTwoArgFunction.apply(0.0), 0.00001);
        Assert.assertEquals(0.0, cosTwoArgFunction.apply(Math.PI/4), 0.00001);
        Assert.assertEquals(-1.0, cosTwoArgFunction.apply(Math.PI/2), 0.00001);
        Assert.assertEquals(0.0, cosTwoArgFunction.apply(3*Math.PI/4), 0.00001);
    }
}