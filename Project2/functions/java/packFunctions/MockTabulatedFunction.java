// mock-объект для того, чтобы потом можно было протестировать класс абстрактных
// табулированных функций
package packFunctions;

import java.util.Iterator;

public  class MockTabulatedFunction extends AbstractTabulatedFunction  {
    // ну все поля приватные завершенные с переменными
    private final double x0;
    private final double x1;
    private final double y0;
    private final double y1;

    // конструктор
    public MockTabulatedFunction(double x0, double x1, double y0, double y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        count = 2;
    }

    @Override
    // ну и как-то простенько реализуем методы для табулированной функции
    protected int floorIndexOfX(double x) {
        if (x < x0) {
            return 0;
        } else if (x == x1) {
            return count-1;
        }
        else return count;
    }
    @Override
    protected double extrapolateLeft(double x) {
        return y0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return y1;
    }

    public Iterator<Point> iterator() {
        return null;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        double k = (y1 - y0) / (x1 - x0);
        return y0 + k * (x - x0);
    }

    @Override
    public int getCount() {
        return count;
    }
    public double getX(int index) {
        if (index == 0) {
            return x0;
        } else if (index == 1) {
            return x1;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    public double getY(int index) {
        if (index == 0) {
            return y0;
        } else if (index == 1) {
            return y1;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    public void setY(int index, double value) {
    }

    public int indexOfX(double xValue) {
        if (xValue == x0) {
            return 0;
        } else if (xValue == x1) {
            return 1;
        } else {
            return -1;
        }
    }

    public int indexOfY(double yValue) {
        if (yValue == y0) {
            return 0;
        } else if (yValue == y1) {
            return 1;
        } else {
            return -1;
        }
    }

    public double leftBound() {
        return Math.min(x0, x1);
    }

    public double rightBound() {
        return Math.max(x0, x1);
    }
}

