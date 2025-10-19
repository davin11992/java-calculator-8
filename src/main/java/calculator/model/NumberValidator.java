package calculator.model;

public class NumberValidator {
    private static final String POSITIVE_INTEGER_REGEX = "^[1-9]\\d*$";

    public static void validateNumber(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("구분자 뒤에는 숫자를 입력해야 합니다.");
        }
        if (!number.equals(number.trim())) {
            throw new IllegalArgumentException("숫자와 구분자 사이에 공백이 있으면 안 됩니다.");
        }
        if (!number.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException("양의 정수만 입력 가능합니다.");
        }
    }
}
