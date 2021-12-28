package com.jsheets.components.contextual_actions;

import java.awt.Color;

import javax.swing.JTextField;

import com.jsheets.components.cells.Cell;

public class CellSpan extends JTextField {
  public CellSpan() {
    super();
    setColumns(7);
    setHorizontalAlignment(CENTER);
    setEnabled(false);
    setDisabledTextColor(Color.BLACK);
  }

  public void updateText(int[] rows, int[] cols) {
    var firstCell = Cell.formatPosition(rows[0], cols[0]);
    var lastCell = Cell.formatPosition(
      rows[rows.length - 1],
      cols[cols.length - 1]
    );

    var cellName = firstCell.equals(lastCell)
      ? firstCell
      : firstCell + ":" + lastCell;

    setText(cellName);
  }
}
