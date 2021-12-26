package com.jsheets.model;

import javax.swing.AbstractListModel;
import javax.swing.table.TableModel;

public class RowHeaderModel extends AbstractListModel<String> {
  private final TableModel sheet;

  public RowHeaderModel(TableModel sheet) {
    this.sheet = sheet;
  }

  public int getSize() {
    return sheet.getRowCount();
  }

  public String getElementAt(int index) {
    return "" + (index + 1);
  }
}