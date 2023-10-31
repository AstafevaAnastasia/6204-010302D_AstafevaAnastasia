// Определяем интерфейс TabulatedFunction, расширяющий интерфейс MathFunction.
// В нем объявлены методы для работы с табличной функцией:
package packFunctions;

import java.util.Iterator;

interface TabulatedFunction extends MathFunction, Iterable<Point>{
    int getCount(); // - возвращает количество точек в табличной функции.
    double getX(int index); // - возвращает значение аргумента для точки с заданным индексом в табличной функции.
    double getY(int index); // - возвращает значение функции для точки с заданным индексом в табличной функции.
    void setY(int index, double value); // - устанавливает значение функции для точки с заданным индексом в табличной функции.
    int indexOfX(double x); // - возвращает индекс точки с заданным значением аргумента в табличной функции.
    int indexOfY(double y); // - возвращает индекс точки с заданным значением функции в табличной функции.
    double leftBound(); // - возвращает значение аргумента первой точки в табличной функции.
    double rightBound(); // - возвращает значение аргумента последней точки в табличной функции.
    @Override
    default Iterator<Point> iterator() {
        throw new UnsupportedOperationException("Iterator is not supported");
    }
}