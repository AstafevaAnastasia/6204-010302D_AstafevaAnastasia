package packFunctions;

public class Point {
    public final double x;
    public final double y;

    public Point(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }
}

