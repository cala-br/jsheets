package com.jsheets.components.cells;

import com.jsheets.components.expressions.ExpressionTree;
import com.jsheets.exceptions.ParseException;

public class ExpressionCell extends Cell<ExpressionTree<?>> {
  public ExpressionCell(String expression, int row, int col) {
    super(expression, row, col);
  }

  @Override
  protected ExpressionTree<?> parse(String expression) throws ParseException {
    return ExpressionTree.parse(expression);
  }
}
