package com.jsheets.events;

import com.jsheets.cells.Cell;
import com.jsheets.model.WorkSheetModel;

/**
 * Fired when a {@link Cell} was updated from the {@link WorksheetModel}.
 */
public class CellUpdatedEventArgs extends EventArgs<WorkSheetModel> {
  /**
   * The cell that was updated.
   */
  public final Cell<?> cell;

  public CellUpdatedEventArgs(WorkSheetModel sender, Cell<?> cell) {
    super(sender);
    this.cell = cell;
  }
}
