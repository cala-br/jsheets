package com.jsheets.expressions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ExpressionTokenizer {
  public static final List<String> operators = Arrays.asList(
    "+", "-", "*", "/", "(", ")", "&&", "||", "<", "!"
  );

  public static List<String> tokenize(String expression) {
    final var result = expression
      .replaceAll(" ", "")
      .split(getPattern());

    return Arrays.asList(result);
  }

  private static String getPattern() {
    final var WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    return String.join("|",
      operators
        .stream()
        .map(o -> Pattern.quote(o))
        .map(o -> String.format(WITH_DELIMITER, o))
        .toList()
    );
  }
}
