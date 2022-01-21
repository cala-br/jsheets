package com.jsheets.components.worksheet;

import javax.swing.JList;
import javax.swing.JTable;

import com.jsheets.model.RowHeaderModel;

/**
 * An header that can wrap a {@link TableScrollPane}, rendering
 * the row number on its left.
 */
public class RowHeader extends JList<String> {
  /**
   * Creates a new {@code TableScrollPane} that wraps
   * around the given pane.
   * @param pane The pane to wrap
   * @return The given pane, wrapped.
   */
  public static TableScrollPane wrap(TableScrollPane pane) {
    new RowHeader(pane, pane.getTable());
    return pane;
  }

  private RowHeader(TableScrollPane pane, JTable table) {
    super(new RowHeaderModel(table.getModel()));

    setFixedCellWidth(50);
    setFixedCellHeight(table.getRowHeight());
    setCellRenderer(new RowHeaderRenderer(table));

    pane.setRowHeaderView(this);
    table.setAutoResizeMode(Worksheet.AUTO_RESIZE_OFF);
  }
}
