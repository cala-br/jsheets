package com.jsheets.components.cells;

public enum CellKind {
  NUMERIC,
  TEXT,
  EXPRESSION,
  ERROR;

  public static CellKind fromExpression(String expression) {
    return fromChar(expression.charAt(0));
  }

  public static CellKind fromChar(char c) {
    return (
      Character.isDigit(c)  ? NUMERIC :
      Character.isLetter(c) ? TEXT :
      c == '='              ? EXPRESSION : ERROR
    );
  }
}
