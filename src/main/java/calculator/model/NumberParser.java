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

            CustomDelimiterHandler.validateCustomDelimiter(customDelimiter);
            delimiter = CustomDelimiterHandler.createDelimiterPattern(customDelimiter);
        }

        return numbers.split(delimiter, -1);
    }
}
