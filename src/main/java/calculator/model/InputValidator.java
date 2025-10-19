package calculator.model;

public class InputValidator {
    public static boolean isEmptyInput(String inputExpression) {
        return inputExpression == null || inputExpression.trim().isEmpty();
    }
}
