package packFunctions;

import java.util.Iterator;

public class StrictTabulatedFunction implements TabulatedFunction  {

    private final TabulatedFunction function; // хранит ссылку на объект другой функции, переданный в конструкторе.

    // Объявляем конструктор класса, который принимает объект другой функции и сохраняет его в поле function
    public StrictTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }

    public int getCount() {
        return function.getCount();
    }

    public double getX(int index) {
        return function.getX(index);
    }

    public double getY(int index) {
        return function.getY(index);
    }

    public void setY(int index, double value) {
        function.setY(index, value);
    }

    public int indexOfX(double x) {
        int index = function.indexOfX(x);
        if (index == -1) {
            // Если точка не найдена, бросаем исключение UnsupportedOperationException
            throw new UnsupportedOperationException("Interpolation is not allowed");
        }
        return index;
    }

    public int indexOfY(double y) {
        return function.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return function.leftBound();
    }

    public double rightBound() {
        return function.rightBound();
    }

    @Override
    public Iterator<Point> iterator() {
        return null;
    }

    // В данном случае мы бросаем исключение UnsupportedOperationException для запрета интерполяции
    public double apply(double x) {
        throw new UnsupportedOperationException("Interpolation is not allowed");
    }
}
