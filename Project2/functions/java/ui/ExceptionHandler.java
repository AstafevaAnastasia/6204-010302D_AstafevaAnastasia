package ui;

import javax.swing.JOptionPane;

public class ExceptionHandler {
    public static void handleException(Exception e) {
        String errorMessage = getErrorMessage(e);
        displayError(errorMessage);
    }

    private static String getErrorMessage(Exception e) {
        if (e instanceof NumberFormatException) return "Ошибка: Некорректное числовое значение.";
        else if (e instanceof ArrayIndexOutOfBoundsException) return "Ошибка: Индекс выходит за границы массива.";
        else if (e instanceof IllegalArgumentException) return "Ошибка: Неверные аргументы.";
        else return "Ошибка: " + e.getMessage();
    }

    private static void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}