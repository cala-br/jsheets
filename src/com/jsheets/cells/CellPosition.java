package com.jsheets.cells;

/**
 * Represents the position at which a cell
 * is located.
 */
public class CellPosition {
  public final int row;
  public final int col;

  /**
   * @param row The row at which the cell is located.
   * @param col The column at which the cell is located.
   */
  public CellPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }


  /**
   * Tells wether the given row and column
   * match this position.
   * @param row The queried row.
   * @param column The queried column.
   * @return
   *  {@code true} if this cell is at the given position,
   *  {@code false} otherwise.
   */
  public boolean isAt(int row, int column) {
    return this.row == row && this.col == column;
  }

  /**
   * Tells wether this position is equal to another.
   */
  @Override
  public boolean equals(Object o) {
    return o instanceof CellPosition
      ? ((CellPosition)o).isAt(row, col)
      : false;
  }

  /**
   * Converts this position to a string format. (E.g A1).
   */
  @Override
  public String toString() {
    final var colName = (char)(col + 65);
    final var rowName = Integer.toString(row + 1);

    return colName + rowName;
  }

  /**
   * Extrapolates a {@code CellPosition} from a position
   * string. (E.g A1)
   * @param position The position string.
   * @return A new {@code CellPosition} object.
   */
  public static CellPosition from(String position) {
    final var columnId = position.charAt(0);
    final var rowId = position.substring(1);

    final var col = columnId - 65;
    final var row = Integer.parseInt(rowId) - 1;

    return new CellPosition(row, col);
  }


  /**
   * Tells wether the given position string matches
   * the column - row pattern.
   * <br>
   * A1 -> {@code true}
   * 2A -> {@code false}
   * @return
   *  {@code true} if the given position is valid,
   *  {@code false} otherwise.
   */
  public static boolean isValidPosition(String position) {
    return position.matches("[A-Z][1-9][0-9]*");
  }
}
