package com.jsheets.events;

import com.jsheets.cells.CellSpan;
import com.jsheets.components.worksheet.Worksheet;

/**
 * Fired when a span of cells is selected on a {@code Worksheet}.
 */
public class CellSelectionEventArgs extends EventArgs<Worksheet> {
  /**
   * The span of cells that were selected.
   */
  public final CellSpan data;

  /**
   * The selected rows.
   */
  public final int[] rows;

  /**
   * The selected columns.
   */
  public final int[] columns;

  /**
   * Tells wether the selection affected only one
   * cell.
   */
  public final boolean hasSingleCell;

  public CellSelectionEventArgs(Worksheet sender, CellSpan data, int[] rows, int[] columns) {
    super(sender);
    this.data = data;
    this.rows = rows;
    this.columns = columns;
    this.hasSingleCell = data.size() == 1;
  }
}
