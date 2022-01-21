package com.jsheets.cells;

import com.jsheets.util.NumberUtil;

/**
 * Represents the various kinds of cells.
 */
public enum CellKind {
  NUMERIC,
  TEXT,
  EXPRESSION,
  ERROR;

  /**
   * Extrapolates a {@code CellKind} from a given expression.
   * @param expression The expression to extract the kind from.
   * @return The kind of cell that could compute this expression.
   */
  public static CellKind fromExpression(String expression) {
    return (
      NumberUtil.isNumber(expression) ? NUMERIC :
      expression.startsWith("=") ? EXPRESSION : TEXT
    );
  }
}
