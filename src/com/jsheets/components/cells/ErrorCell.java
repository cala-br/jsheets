package com.jsheets.components.cells;

public class ErrorCell extends TextCell {
  public ErrorCell(String expression, int row, int column) {
    super(row, column);
    setExpression(expression);
    setValue("#ERR");
  }
}
