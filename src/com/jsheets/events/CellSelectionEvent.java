package com.jsheets.events;

import com.jsheets.components.cells.CellSpan;
import com.jsheets.components.worksheet.Worksheet;

public class CellSelectionEvent extends EventArgs<Worksheet> {
  public final CellSpan data;
  public final int[] rows;
  public final int[] columns;
  public final boolean hasSingleCell;

  public CellSelectionEvent(Worksheet sender, CellSpan data, int[] rows, int[] columns) {
    super(sender);
    this.data = data;
    this.rows = rows;
    this.columns = columns;
    this.hasSingleCell = data.size() == 1;
  }
}
