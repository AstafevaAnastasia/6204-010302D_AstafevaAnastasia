package concurrent;
import packFunctions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction tabulatedFunc;

    // ����������� ������, ����������� �� ���� ������ ���� TabulatedFunction
    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunc = tabulatedFunction;

    }

    public void run() {
        // ���������� �� ���� ��������� �������
        for (int i = 0; i < tabulatedFunc.getCount(); i++) {
            // �������������� ������ � �������
            synchronized (tabulatedFunc) {
                // ��������� ������ ��� ������ �� �����
                String str = String.format("After read: i = %d, x = %f, y = %f", i, tabulatedFunc.getX(i), tabulatedFunc.getY(i));
                System.out.println(str);
            }
        }
    }

}