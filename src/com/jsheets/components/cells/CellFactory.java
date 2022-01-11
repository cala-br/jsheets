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
      switch (kind) {
        case NUMERIC: return new NumericCell(p);
        case TEXT: return new TextCell(p);
        case EXPRESSION: return new ExpressionCell(p);
        default: return new ErrorCell(p);
      }
    }
    catch (ParseException e) {
      return new ErrorCell(p);
    }
  }
}
