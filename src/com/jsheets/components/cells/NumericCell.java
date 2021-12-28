package com.jsheets.components.cells;

import com.jsheets.exceptions.ParseException;

public class NumericCell extends Cell<Number> {
  public NumericCell(String expression, int row, int col) {
    super(expression, row, col);
  }

  @Override
  protected Number parse(String expression) throws ParseException {
    try {
      return Double.parseDouble(expression);
    }
    catch (NumberFormatException e) {
      throw new ParseException();
    }
  }
}
