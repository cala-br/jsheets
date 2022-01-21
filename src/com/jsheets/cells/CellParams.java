package com.jsheets.cells;

/**
 * Parameters that are necessary to
 * create a new cell.
 */
public class CellParams {
  public final int row;
  public final int column;
  public final CellView view;
  public final String expression;

  /**
   * Creates a new {@code CellParams} object.
   * @param expression The cell's expression.
   * @param row The cell's row.
   * @param column The cell's column.
   * @param view The view of the matrix containing all the cells.
   */
  public CellParams(String expression, int row, int column, CellView view) {
    this.row = row;
    this.column = column;
    this.view = view;
    this.expression = expression;
  }
}
