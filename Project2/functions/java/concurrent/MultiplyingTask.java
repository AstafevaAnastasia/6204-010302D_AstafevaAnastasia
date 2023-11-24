package concurrent;
import packFunctions.TabulatedFunction;

public class MultiplyingTask implements Runnable{

    private final TabulatedFunction tabulatedFunction;

    public MultiplyingTask (TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }
    @Override
    public void run() {
        for(int i = 0; i < tabulatedFunction.getCount(); ++i) {
            tabulatedFunction.setY(i, tabulatedFunction.getY(i) * 2);
        }
        System.out.println("Поток " + Thread.currentThread().getName() + " закончил свою работу");
    }
}
