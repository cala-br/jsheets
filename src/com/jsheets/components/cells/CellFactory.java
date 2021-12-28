package com.jsheets.components.cells;

import com.jsheets.exceptions.ParseException;
import com.jsheets.util.StringUtil;

public class CellFactory {
  public static Cell<?> create(String expression, int row, int col) {
    if (StringUtil.isNullOrEmpty(expression)) {
      return new TextCell(row, col);
    }

    try {
      final var kind = CellKind.fromExpression(expression);
      return switch (kind) {
        case NUMERIC    -> new NumericCell(expression, row, col);
        case TEXT       -> new TextCell(expression, row, col);
        case EXPRESSION -> new ExpressionCell(expression, row, col);
        case ERROR      -> new ErrorCell(expression, row, col);
      };
    }
    catch (ParseException e) {
      return new ErrorCell(expression, row, col);
    }
  }
}
