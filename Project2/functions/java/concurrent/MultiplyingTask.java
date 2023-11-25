package concurrent;
import packFunctions.TabulatedFunction;

public class MultiplyingTask implements Runnable{

    private final TabulatedFunction tabulatedFunction;
    private boolean completed;

    public MultiplyingTask (TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }
    @Override
    public void run() {
        for(int i = 0; i < tabulatedFunction.getCount(); ++i) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, tabulatedFunction.getY(i) * 2);
                completed = true;
            }
        }
        System.out.println(Thread.currentThread().getName() + "done");
    }
    public boolean isCompleted() {
        return completed;
    }
}
