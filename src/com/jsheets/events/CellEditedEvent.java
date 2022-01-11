package com.jsheets.events;

import com.jsheets.cells.Cell;
import com.jsheets.components.worksheet.Worksheet;

public class CellEditedEvent extends EventArgs<Worksheet> {
  public final Cell<?> cell;

  public CellEditedEvent(Worksheet sender, Cell<?> cell) {
    super(sender);
    this.cell = cell;
  }
}
