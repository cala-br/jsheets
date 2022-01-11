package com.jsheets.components.worksheet;

import javax.swing.JList;
import javax.swing.JTable;

import com.jsheets.model.RowHeaderModel;

public class RowHeader extends JList<String> {
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
