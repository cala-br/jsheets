package com.jsheets.components.cells;

import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.ExpressionTree;

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
