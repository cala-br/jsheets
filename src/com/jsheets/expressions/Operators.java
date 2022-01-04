package com.jsheets.expressions;

import java.util.ArrayList;
import java.util.List;

public class Operators {
  private static final List<String> lowestPrecedence = List.of("&&", "||");
  private static final List<String> lowerPrecedence = List.of("<", ">", "<=", ">=", "==", "!=");
  private static final List<String> normalPrecedence = List.of("+", "-");
  private static final List<String> highPrecedence = List.of("/", "*", "%");
  private static final List<String> highestPrecedence = List.of("!");

  private static final List<String> unary = highPrecedence;
  private static final List<String> parentheses = List.of("(", ")");

  private static final List<String> logical = new ArrayList<>() {{
    addAll(lowestPrecedence);
    addAll(lowerPrecedence);
  }};

  private static final List<String> arithmetic = new ArrayList<>() {{
    addAll(normalPrecedence);
    addAll(highPrecedence);
  }};

  private static final List<String> binary = new ArrayList<>() {{
    addAll(arithmetic);
    addAll(logical);
  }};

  private static final List<String> all = new ArrayList<>() {{
    addAll(binary);
    addAll(unary);
    addAll(parentheses);
  }};


  public static List<String> getAll() {
    return all;
  }


  public static List<String> getAllByLargestPossibleToken() {
    return getAll()
      .stream()
      .sorted((a, b) -> b.length() - a.length())
      .toList();
  }


  public static int getPrecedence(String o) {
    return lowestPrecedence.contains(o)
      ? 1
      : lowerPrecedence.contains(o)
        ? 2
        : normalPrecedence.contains(o)
          ? 3
          : highPrecedence.contains(o)
            ? 4
            : highestPrecedence.contains(o)
              ? 5
      : -1;
  }


  public static boolean isOperator(String o) {
    return getAll().contains(o);
  }

  public static boolean isParenthesis(String o) {
    return parentheses.contains(o);
  }

  public static boolean isBinary(String o) {
    return binary.contains(o);
  }

  public static boolean isUnary(String o) {
    return unary.contains(o);
  }

  public static boolean isLogical(String o) {
    return logical.contains(o);
  }

  public static boolean isArithmetic(String o) {
    return arithmetic.contains(o);
  }
}
