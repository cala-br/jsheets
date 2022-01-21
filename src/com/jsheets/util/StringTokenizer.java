package com.jsheets.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Provides methods to split a given string into tokens,
 * while keeping the tokens.
 */
public class StringTokenizer {
  /**
   * Splits the string while keeping the given tokens.
   * <pre>
   *final var str = "a+b*c";
   *final var tokens = List.of("+", "*");
   *final var result = StringTokenizer.splitKeepingTokens(str, tokens);
   * 
   * // result == ["a", "+", "b", "*", "c"]
   * </pre>
   * @param expression The expression to split.
   * @param delimiters The tokens to use for the split.
   * @return
   *  A new {@code List} containing the tokens.
   */
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

  /**
   * Splits the string while keeping the delimiter
   * <pre>
   *final var str = "a+b+c";
   *final var token = "+";
   *final var result = StringTokenizer.splitKeepingToken(str, token);
   *
   * // result == ["a", "+", "b", "+", "c"]
   * </pre>
   * @param expression The expression to split.
   * @param token The token to use for the split.
   * @retun
   * A new {@code List} containing the split string.
   */
  public static List<String> splitKeepingToken(String expression, String token) {
    final var keepDelimiter = "((?<=%1$s)|(?=%1$s))";
    final var result = expression.split(
      String.format(keepDelimiter, Pattern.quote(token))
    );

    return Arrays.asList(result);
  }
}
