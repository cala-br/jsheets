package com.jsheets.cells;

public class ErrorCell extends TextCell {
  public ErrorCell(CellParams params) {
    super(params);
    setExpression(params.expression);
    setValue("#ERR");
  }
}
