package com.jsheets.cells;

/**
 * A cell representing an error.
 */
public class ErrorCell extends TextCell {
  public ErrorCell(CellParams params) {
    super(params);
    setExpression(params.expression);
    setValue("#ERR");
  }
}
