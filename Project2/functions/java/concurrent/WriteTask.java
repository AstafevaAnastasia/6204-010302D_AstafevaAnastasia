package concurrent;
import packFunctions.TabulatedFunction;

public class WriteTask implements Runnable {
    private TabulatedFunction function;
    private double value;

    // ����������� ������, ����������� �� ���� ������ ���� TabulatedFunction � �������� ���� double
    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.function = tabulatedFunction;
        this.value = value;
    }

    public void run() {
        // ���������� �� ���� ��������� �������
        for (int i = 0; i < function.getCount(); i++) {
            function.setY(i, value);  // ������������� �������� value ��� y-���������� �������� � �������� i
            String s = String.format("Writing for index %d complete ", i);
            System.out.println(s);
        }
    }

}