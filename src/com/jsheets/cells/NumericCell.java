package com.jsheets.cells;

import com.jsheets.exceptions.ParseException;

public class NumericCell extends Cell<Number> {
  public NumericCell(CellParams params) {
    super(params);
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
