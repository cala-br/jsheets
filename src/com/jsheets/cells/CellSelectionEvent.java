package com.jsheets.cells;

public class CellSelectionEvent {
  public final CellSpan data;
  public final int[] rows;
  public final int[] columns;
  public final boolean hasSingleCell;

  public CellSelectionEvent(CellSpan data, int[] rows, int[] columns) {
    this.data = data;
    this.rows = rows;
    this.columns = columns;
    this.hasSingleCell = data.size() == 1;
  }
}
