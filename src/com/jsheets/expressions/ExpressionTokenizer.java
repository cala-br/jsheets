package com.jsheets.expressions;

import java.util.List;
import java.util.stream.Collectors;

import com.jsheets.util.StringTokenizer;

public class ExpressionTokenizer {
  /**
   * Split an expression into tokens.
   * @param expression The expression to split.
   * @return
   *  A list of tokens, representing operands and operators of the expression.
   */
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
