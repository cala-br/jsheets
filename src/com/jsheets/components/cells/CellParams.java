package com.jsheets.components.cells;

public class CellParams {
  public final int row;
  public final int column;
  public final CellView view;
  public final String expression;

  public CellParams(String expression, int row, int column, CellView view) {
    this.row = row;
    this.column = column;
    this.view = view;
    this.expression = expression;
  }
}
