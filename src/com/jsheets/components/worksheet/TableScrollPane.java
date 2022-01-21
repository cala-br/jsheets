package com.jsheets.components.worksheet;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * A scroll pane that renders the header of {@link JTable}s
 */
public class TableScrollPane extends JScrollPane {
  private final int rowsToShow;
  private final JTable table;

  /**
   * @return
   *  The table wrapped within this pane.
   */
  public JTable getTable() {
    return table;
  }


  /**
   * Create a new {@code TableScrollPane} that renders the
   * given table, showing all its rows.
   * @param table
   *  The table to render.
   */
  public TableScrollPane(JTable table) {
    this(table, table.getRowCount());
  }

  /**
   * Create a new {@code TableScrollPane} that renders the
   * given table with the provided number of rows.
   * @param table The table to render.
   * @param rowsToShow The rows to show.
   */
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