package com.jsheets.events;

import com.jsheets.components.cells.Cell;
import com.jsheets.model.WorkSheetModel;

public class CellUpdatedEvent extends EventArgs<WorkSheetModel> {
  public final Cell<?> cell;

  public CellUpdatedEvent(WorkSheetModel sender, Cell<?> cell) {
    super(sender);
    this.cell = cell;
  }
}
