package com.jsheets.components.work_sheet;

public class CellSelectionEvent {
  public final CellList data;
  public final int[] rows;
  public final int[] columns;
  public final boolean hasSingleCell;

  public CellSelectionEvent(CellList data, int[] rows, int[] columns) {
    this.data = data;
    this.rows = rows;
    this.columns = columns;
    this.hasSingleCell = data.size() == 1;
  }
}
