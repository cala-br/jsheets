package com.jsheets.components.worksheet;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;

/**
 * Renderer for the {@link RowHeader}
 */
public class RowHeaderRenderer extends JLabel implements ListCellRenderer<Object> {
  private final JTable table;

  public RowHeaderRenderer(JTable table) {
    this.table = table;
  }

  /**
   * Renders a {@link TableHeaderCell} for the given value.
   */
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
