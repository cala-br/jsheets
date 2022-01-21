package com.jsheets.cells;

import com.jsheets.exceptions.ParseException;
import com.jsheets.util.StringUtil;

/**
 * A factory for creating cells.
 */
public class CellFactory {
  /**
   * Tries to create a new cell base on the given params.
   * If a parse exception is encountered, an {@link ErrorCell} is returned.
   *
   * @return
   *  A new {@code Cell} of the correct kind.
   */
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
