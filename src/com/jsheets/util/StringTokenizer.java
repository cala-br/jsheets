package com.jsheets.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringTokenizer {
  public static List<String> splitKeepingTokens(
    String expression,
    List<String> delimiters
  ) {
    var result = List.of(expression);
    for (final var delimiter : delimiters) {
      result = split(result, delimiters, delimiter);
    }

    return result;
  }

  private static List<String> split(
    List<String> expression,
    List<String> delimiters,
    String delimiter
  ) {
    return expression
      .stream()
      .flatMap(e -> {
        return delimiters.contains(e)
          ? List.of(e).stream()
          : splitKeepingToken(e, delimiter).stream();
      })
      .collect(Collectors.toList());
  }

  public static List<String> splitKeepingToken(String expression, String token) {
    final var keepDelimiter = "((?<=%1$s)|(?=%1$s))";
    final var result = expression.split(
      String.format(keepDelimiter, Pattern.quote(token))
    );

    return Arrays.asList(result);
  }
}
