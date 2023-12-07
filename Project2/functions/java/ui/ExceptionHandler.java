package ui;

import javax.swing.JOptionPane;

public class ExceptionHandler {
    public static void handleException(Exception e) {
        String errorMessage = getErrorMessage(e);
        displayError(errorMessage);
    }

    private static String getErrorMessage(Exception e) {
        if (e instanceof NumberFormatException) return "������: ������������ �������� ��������.";
        else if (e instanceof ArrayIndexOutOfBoundsException) return "������: ������ ������� �� ������� �������.";
        else if (e instanceof IllegalArgumentException) return "������: �������� ���������.";
        else return "������: " + e.getMessage();
    }

    private static void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "������", JOptionPane.ERROR_MESSAGE);
    }
}