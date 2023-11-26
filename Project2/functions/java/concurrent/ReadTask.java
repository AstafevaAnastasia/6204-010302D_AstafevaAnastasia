package concurrent;
import packFunctions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction function;

    // ����������� ������, ����������� �� ���� ������ ���� TabulatedFunction
    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.function = tabulatedFunction;

    }

    public void run() {
        // ���������� �� ���� ��������� �������
        for (int i = 0; i < function.getCount(); i++) {
            // �������������� ������ � �������
            synchronized (function) {
                // ��������� ������ ��� ������ �� �����
                String str = String.format("After read: i = %d, x = %f, y = %f", i, function.getX(i), function.getY(i));
                System.out.println(str);
            }
        }
    }

}