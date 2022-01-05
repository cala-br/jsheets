package com.jsheets.components.cells;

import com.jsheets.exceptions.ParseException;
import com.jsheets.util.StringUtil;

public class CellFactory {
  public static Cell<?> create(CellParams p) {
    if (StringUtil.isNullOrEmpty(p.expression)) {
      return new TextCell(p);
    }

    try {
      final var kind = CellKind.fromExpression(p.expression);
      return switch (kind) {
        case NUMERIC    -> new NumericCell(p);
        case TEXT       -> new TextCell(p);
        case EXPRESSION -> new ExpressionCell(p);
        case ERROR      -> new ErrorCell(p);
      };
    }
    catch (ParseException e) {
      return new ErrorCell(p);
    }
  }
}
