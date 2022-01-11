package com.jsheets.cells;

import com.jsheets.util.NumberUtil;

public enum CellKind {
  NUMERIC,
  TEXT,
  EXPRESSION,
  ERROR;

  public static CellKind fromExpression(String expression) {
    return (
      NumberUtil.isNumber(expression) ? NUMERIC :
      expression.startsWith("=") ? EXPRESSION : TEXT
    );
  }
}
