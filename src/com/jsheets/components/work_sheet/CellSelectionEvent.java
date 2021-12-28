package com.jsheets.components.work_sheet;

public class CellSelectionEvent {
  public final CellSelection data;
  public final int[] rows;
  public final int[] columns;
  public final boolean hasSingleCell;

  public CellSelectionEvent(CellSelection data, int[] rows, int[] columns) {
    this.data = data;
    this.rows = rows;
    this.columns = columns;
    this.hasSingleCell = data.size() > 1;
  }
}
