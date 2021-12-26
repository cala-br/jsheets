package com.jsheets.components.work_sheet;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableScrollPane extends JScrollPane {
  private final int rowsToShow;
  private final JTable table;

  public JTable getTable() {
    return table;
  }


  public TableScrollPane(JTable table) {
    this(table, table.getRowCount());
  }

  public TableScrollPane(JTable table, int rowsToShow) {
    super(table);
    this.table = table;
    this.rowsToShow = rowsToShow;

    resizeToFit();
  }

  private void resizeToFit() {
    var tableHeight = getTableHeight();
    var insetsHeight = getInsetsHeight();
    setPreferredSize(
      new Dimension(
        getPreferredSize().width,
        insetsHeight + tableHeight
      )
    );
  }

  private int getTableHeight() {
    return
      table.getRowHeight() * rowsToShow +
      table.getIntercellSpacing().height * (rowsToShow + 1) +
      table.getTableHeader().getPreferredSize().height;
  }

  private int getInsetsHeight() {
    var insets = getInsets();
    return insets.top + insets.bottom;
  }
}