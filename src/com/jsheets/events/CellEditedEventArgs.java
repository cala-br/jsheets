package com.jsheets.events;

import com.jsheets.cells.Cell;
import com.jsheets.components.worksheet.Worksheet;

/**
 * Fired when a {@link Cell} is edited from the {@link Worksheet}.
 */
public class CellEditedEventArgs extends EventArgs<Worksheet> {
  /**
   * The cell that was edited.
   */
  public final Cell<?> cell;

  public CellEditedEventArgs(Worksheet sender, Cell<?> cell) {
    super(sender);
    this.cell = cell;
  }
}
