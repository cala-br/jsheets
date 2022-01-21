package com.jsheets.cells;

import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.ExpressionTree;

/**
 * A cell representing an expression.
 * <br>
 * E.g. {@code A1 + B2 * (C3 - 12)}
 */
public class ExpressionCell extends Cell<String> {
  public ExpressionCell(CellParams params) {
    super(params);
  }

  @Override
  protected String parse(String expression) throws ParseException {
    return ExpressionTree
      .parse(expression.substring(1), getCellView())
      .compute()
      .toString();
  }
}
