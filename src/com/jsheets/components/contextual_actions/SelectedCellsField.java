package com.jsheets.components.contextual_actions;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import com.jsheets.cells.CellPosition;

/**
 * A field that displays the position of the
 * cells selected inside the {@link Worksheet}.
 */
public class SelectedCellsField extends JTextField {
  public SelectedCellsField() {
    super();
    setColumns(7);
    setHorizontalAlignment(CENTER);
    setEnabled(false);
    setDisabledTextColor(Color.BLACK);
    makeBoxLayoutRespectSize();
  }

  private void makeBoxLayoutRespectSize() {
    setMaximumSize(new Dimension(100, 100));
  }


  /**
   * Displays the span of selected cells.
   * @param rows The rows that have been selected.
   * @param cols The columns that have been selected.
   */
  public void updateText(int[] rows, int[] cols) {
    final var firstCell = new CellPosition(rows[0], cols[0]);
    final var lastCell = new CellPosition(
      rows[rows.length - 1],
      cols[cols.length - 1]
    );

    final var cellName = firstCell.equals(lastCell)
      ? firstCell.toString()
      : firstCell + ":" + lastCell;

    setText(cellName);
  }
}
