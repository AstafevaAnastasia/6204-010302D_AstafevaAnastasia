package concurrent;
import packFunctions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 10);
        List<Thread> list = new ArrayList<>();
        Collection<MultiplyingTask> tasks = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; ++i) {
            MultiplyingTask task = new MultiplyingTask(tabulatedFunction);
            tasks.add(task);
            list.add(new Thread(task));
        }
        for (Thread thread : list) {
            thread.start();
        }
        boolean allTasksCompleted = false;
        while (!allTasksCompleted) {
            allTasksCompleted = true;
            Iterator<MultiplyingTask> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                MultiplyingTask task = iterator.next();
                if (task.isCompleted()) {
                    iterator.remove();
                } else {
                    allTasksCompleted = false;
                }
            }
        }

        System.out.println("Tabulated Function:");
        for (int i = 1; i <= 10; i++) {
            double y = tabulatedFunction.apply(i);
            System.out.println("f(" + (double) i + ") = " + y);
        }
    }
}
