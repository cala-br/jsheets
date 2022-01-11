package com.jsheets.expressions;

import java.util.List;
import java.util.stream.Collectors;

import com.jsheets.util.StringTokenizer;

public class ExpressionTokenizer {
  public static List<String> tokenize(String expression) {
    return StringTokenizer.splitKeepingTokens(
      expression.replaceAll(" ", ""),
      Operator
        .getAllByLargestPossibleSymbol()
        .map(o -> o.getSymbol())
        .collect(Collectors.toList())
    );
  }
}
