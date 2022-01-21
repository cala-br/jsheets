package com.jsheets.model;

import javax.swing.AbstractListModel;
import javax.swing.table.TableModel;

/**
 * The model for the {@link RowHeader}.
 */
public class RowHeaderModel extends AbstractListModel<String> {
  private final TableModel sheet;

  /**
   * Creates a new {@code RowHeaderModel} that is synchronized
   * with a given table model, in order to display display at maximum
   * the table's row count.
   * @param sheet
   *  The table model to synchronize with.
   */
  public RowHeaderModel(TableModel sheet) {
    this.sheet = sheet;
  }

  /**
   * Returns the size of the table's model.
   */
  public int getSize() {
    return sheet.getRowCount();
  }

  /**
   * @return
   *  The row number relative to its index.
   *  E.g, {@code 0} will return {@code "1"}.
   */
  public String getElementAt(int index) {
    return "" + (index + 1);
  }
}