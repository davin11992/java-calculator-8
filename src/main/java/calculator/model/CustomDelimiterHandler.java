package calculator.model;

import java.util.regex.Pattern;

public class CustomDelimiterHandler {
    private static final String DEFAULT_DELIMITER = ",|:";
    
    public static void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
        }
        if (customDelimiter.matches("\\d")) {
            throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다.");
        }
    }

    public static String createDelimiterPattern(String customDelimiter) {
        if (customDelimiter.equals(" ")) {
            return DEFAULT_DELIMITER + "|\\s";
        }
        return DEFAULT_DELIMITER + "|" + Pattern.quote(customDelimiter);
    }
}
