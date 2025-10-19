package calculator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberParser {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_DELIMITER_PATTERN = "^//(.*?)(?:\n)(.*)$";

    public static String[] splitByDelimiter(String inputExpression) {
        inputExpression = inputExpression.replace("\\n", "\n");

        String delimiter = DEFAULT_DELIMITER;
        String numbers = inputExpression;

        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_PATTERN);
        Matcher matcher = pattern.matcher(inputExpression);

        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            numbers = matcher.group(2);

            validateCustomDelimiter(customDelimiter);
            delimiter = createDelimiterPattern(customDelimiter);
        }

        return numbers.split(delimiter, -1);
    }

    private static void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
        }
        if (customDelimiter.matches("\\d")) {
            throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다.");
        }
    }

    private static String createDelimiterPattern(String customDelimiter) {
        if (customDelimiter.equals(" ")) {
            return DEFAULT_DELIMITER + "|\\s";
        }
        return DEFAULT_DELIMITER + "|" + Pattern.quote(customDelimiter);
    }
}
