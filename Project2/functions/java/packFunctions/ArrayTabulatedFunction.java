package packFunctions;
import java.util.Arrays;


// класс табулированных функций, значения которых хранятся в массиве
// данный класс расширает реализацию класса AbsractTabulatedFunction и реализует интерфейс Removable
public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Removable, Insertable {
    private double[] xValues; // приватное поле значений x
    private double[] yValues; // приватное поле значений y
    private int count; // приватное поле количества элементов

    // конструктор
    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) { // ну просто обработка исключений
            throw new IllegalArgumentException("The length of xValues and yValues must be the same");
        }
        this.xValues = Arrays.copyOf(xValues, xValues.length); // делаем копию массива с иксами
        this.yValues = Arrays.copyOf(yValues, yValues.length); // делаем копию массива с игреками
        this.count = xValues.length; // ну и значение count тоже устанавливаем
    }

    // еще один конструктор
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) { // меняем местами, если левая граница интервала больше правой
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + step * i;
            this.xValues[i] = x;
            this.yValues[i] = source.apply(x);
        }
    }

    @Override
    public void remove(int index){
        for (int i = index + 1; i < xValues.length; i++) {
            xValues[i - 1] = xValues[i];
            yValues[i - 1] = yValues[i];
        }
        --count;
        xValues = Arrays.copyOf(xValues, xValues.length - 1);
        yValues = Arrays.copyOf(yValues, yValues.length - 1);
        //ArrayTabulatedFunction(newX, newY);
    }

    //Переопределение метода insert из интерфейса Insertable для 3-го задания
    // Метод insert() вставляет точку с заданными значениями аргумента и функции в табличную функцию.
    @Override
    public void insert(double x, double y) {
        int index = indexOfX(x); // Сначала находится индекс точки с заданным значением аргумента в массиве xValues с помощью метода indexOfX().
        if (index != -1) { // Если точка с таким значением аргумента уже есть в табличной функции, то значение функции для этой точки заменяется на новое значение.
            yValues[index] = y;
        } else { // Если точки с таким значением аргумента еще нет в табличной функции, то создаются новые массивы newXValues и newYValues размером на 1 больше, чем у текущих массивов xValues и yValues.
            double[] newXValues = new double[count + 1];
            double[] newYValues = new double[count + 1];
            int insertIndex = 0;
            for (int i = 0; i < count; i++) { // Затем происходит цикл по всем точкам табличной функции, в котором определяется индекс insertIndex новой точки в массивах newXValues и newYValues.
                if (x > xValues[i]) { // Если значение аргумента новой точки больше, чем у текущей точки, то текущая точка копируется в новые массивы, а индекс insertIndex увеличивается на 1.
                    newXValues[i] = xValues[i];
                    newYValues[i] = yValues[i];
                    insertIndex = i + 1;
                } else { // Если значение аргумента новой точки меньше или равно, то текущая точка копируется в новые массивы со сдвигом на 1 индекс вправо.
                    newXValues[i + 1] = xValues[i];
                    newYValues[i + 1] = yValues[i];
                }
            }
            newXValues[insertIndex] = x; // После цикла новая точка с заданными значениями аргумента и функции вставляется в новые массивы на позицию с индексом insertIndex.
            newYValues[insertIndex] = y;
            xValues = newXValues;
            yValues = newYValues;
            ++count; // Новые массивы xValues и yValues заменяют текущие массивы, а количество точек count увеличивается на 1.
        }
    }

    @Override
    public double getX(int index) { // получаем икс
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        return xValues[index];
    }
    @Override
    public double getY(int index) { // получаем игрек
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) { // устанавливаем значение игрека
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of range");
        }
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) { // получаем индекс икса
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) { // получаем индекс игрека
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    } // левая граница

    @Override
    public double rightBound() {
        return xValues[count - 1];
    } //правая граница

    @Override
    public double interpolate(double x, int floorIndex) {
        if (floorIndex < 0 || floorIndex >= count - 1) {
            throw new IllegalArgumentException("Index is out of range");
        }
        double x0 = xValues[floorIndex];
        double x1 = xValues[floorIndex + 1];
        double y0 = yValues[floorIndex];
        double y1 = yValues[floorIndex + 1];
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    // floorIndexOfX
    // метод возвращает 0, если заданный x меньше всевозможных x в массиве
    // возвращает count(количество элементов в массиве), если больше всех элементов массива
    // возвращает индекс элемента массива, если найден элемент с таким же значением
    // и возвращает индекс максимального элемента, который меньше заданного x
    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i < count - 1; ++i) { // начинам искать тогда x, если ни одно из предыдущих условий не выполнено
            if (x < xValues[0]) { // если x меньше первого элемента
                return 0; // то так как массив упорядочен => x меньше всех элементов массива => возвращаем 0
            }
            if (x == xValues[i]) { // если мы нашли x,
                return i; // то возвращаем его индес
            }
            if (x > xValues[i] && x < xValues[i + 1]) { //если мы находим такой промежуток, в котором x больше предыдущего, но меньше следующего
                return i; // то возвращаем индекс предыдущего
            }
        }
        if (x == xValues[count - 1]) { // смотрим, больше равен ли x последнему элементу(т.к. цикл до него не дошел)
            return count - 1; //  если да, то возвращаем его значение
        }
        return count; // ну а в конце возвращаем count, тк остальные условия не выполнилисб
    }
    private int searchIndex(double x) { // вспомогательная функция поиска икса
        int n = xValues.length;
        if (x < xValues[0]) {
            return 0;
        } else if (x > xValues[n - 1]) {
            return n - 2;
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (x >= xValues[i] && x < xValues[i + 1]) {
                    return i;
                }
            }
            // This should never happen if the xValues array is properly sorted
            throw new RuntimeException("Failed to find index for x value: " + x);
        }
    }
    @Override
    protected double extrapolateLeft(double x) {
        int index = searchIndex(x);
        double x1 = xValues[index];
        double x2 = xValues[index + 1];
        double y1 = yValues[index];
        double y2 = yValues[index + 1];
        return y1 + (y2 - y1) / (x2 - x1) * (x - x1);
    }
    public int getCount() { // получаем поле count
        return count;
    }
    // Right extrapolation method
    protected double extrapolateRight(double x) {
        int index = searchIndex(x);
        double x1 = xValues[index];
        double x2 = xValues[index + 1];
        double y1 = yValues[index];
        double y2 = yValues[index + 1];
        return y2 + (y2 - y1) / (x2 - x1) * (x - x2);
    }
    protected double interpolate(double x) {
        int index = searchIndex(x);
        double x1 = xValues[index];
        double x2 = xValues[index + 1];
        double y1 = yValues[index];
        double y2 = yValues[index + 1];
        return y1 + (y2 - y1) / (x2 - x1) * (x - x1);
    }
}
