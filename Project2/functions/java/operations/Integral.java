package operations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;
import packFunctions.*;
import java.util.List;

public class Integral  {
   /* private int numThreads; // Количество потоков для распараллеливания
    private ExecutorService executorService;

    public Integral(int numThreads) {
        this.numThreads = numThreads;
        this.executorService = Executors.newFixedThreadPool(numThreads);
    }

    public double calculate(TabulatedFunction function, double leftX, double rightX) {
        double totalIntegral = 0.0;
        List<Task> tasks = splitRangeIntoTasks(function, leftX, rightX);

        try {
            List<Future<Double>> futures = executorService.invokeAll((Collection<? extends Callable<Double>>) tasks);
            for (Future<Double> future : futures) {
                totalIntegral += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        return totalIntegral;
    }

    private List<Task> splitRangeIntoTasks(TabulatedFunction function, double leftX, double rightX) {
        List<Task> tasksList = new ArrayList<>();
        double rangeSize = (rightX - leftX) / numThreads;

        for (int i = 0; i < numThreads; i++) {
            double taskLeftX = leftX + i * rangeSize;
            double taskRightX = taskLeftX + rangeSize;
            tasksList.add(() -> calculateIntegralOnRange(function, taskLeftX, taskRightX));
        }

        return tasksList;
    }

    private double calculateIntegralOnRange(TabulatedFunction function, double leftX, double rightX) {
        double sum = 0.0;
        double step = 0.0001; // Шаг интегрирования (выберите подходящий для вашей задачи шаг)
        double x = leftX;

        while (x < rightX) {
            double y1 = function.getY(x);
            double y2 = function.getY(x + step);
            double dyAvg = (y1 + y2) / 2.0;
            double dx = step;
            double area = dyAvg * dx;
            sum += area;
            x += step;
        }

        return sum;
    }

    @Override
    public double execute() {
        return 0;
    }*/
}
