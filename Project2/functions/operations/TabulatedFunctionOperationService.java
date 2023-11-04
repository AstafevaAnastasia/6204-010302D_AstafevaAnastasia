package operations;
import packFunctions.TabulatedFunction;
import packFunctions.Point;
public class TabulatedFunctionOperationService {

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] point = new Point[tabulatedFunction.getCount()];
        for (Point poi: tabulatedFunction) {
            point[i++] = poi;
        }
        return point;
    }
}
