package com.jsheets.components.work_sheet;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;

public class RowHeaderRenderer extends JLabel implements ListCellRenderer<Object> {
  private final JTable table;

  public RowHeaderRenderer(JTable table) {
    this.table = table;
  }

  public Component getListCellRendererComponent(
    JList<?> list,
    Object value,
    int index,
    boolean isSelected,
    boolean cellHasFocus
  ) {
    final var content =
      (value != null) ? value.toString() : "";

    return new TableHeaderCell(table, content);
  }
}
