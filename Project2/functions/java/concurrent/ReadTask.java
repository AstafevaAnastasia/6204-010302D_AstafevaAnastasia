package concurrent;
import packFunctions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction tabulatedFunc;

    // конструктор класса, принимающий на вход объект типа TabulatedFunction
    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunc = tabulatedFunction;

    }

    public void run() {
        // проходимся по всем элементам функции
        for (int i = 0; i < tabulatedFunc.getCount(); i++) {
            // синхронизируем доступ к функции
            synchronized (tabulatedFunc) {
                // формируем строку для вывода на экран
                String str = String.format("After read: i = %d, x = %f, y = %f", i, tabulatedFunc.getX(i), tabulatedFunc.getY(i));
                System.out.println(str);
            }
        }
    }

}