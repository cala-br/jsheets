package com.jsheets.components.cells;

public class CellPosition {
  public final int row;
  public final int col;

  public CellPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }


  public boolean isAt(int row, int column) {
    return this.row == row && this.col == column;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof CellPosition
      ? ((CellPosition)o).isAt(row, col)
      : false;
  }

  @Override
  public String toString() {
    final var colName = (char)(col + 65);
    final var rowName = Integer.toString(row + 1);

    return colName + rowName;
  }

  public static CellPosition from(String position) {
    final var columnId = position.charAt(0);
    final var rowId = position.substring(1);

    final var col = columnId - 65;
    final var row = Integer.parseInt(rowId) - 1;

    return new CellPosition(row, col);
  }


  public static boolean isValidPosition(String position) {
    return position.matches("[A-Z][1-9][0-9]*");
  }
}
