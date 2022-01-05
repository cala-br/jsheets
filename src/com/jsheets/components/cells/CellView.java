package com.jsheets.components.cells;

public class CellView {
  private final Cell<?>[][] cells;

  public CellView(Cell<?>[][] cells) {
    this.cells = cells;
  }

  public Cell<?> get(CellPosition pos) {
    return get(pos.row, pos.col);
  }

  public Cell<?> get(int row, int col) {
    return cells[row][col];
  }
}
