package com.jsheets.components.worksheet;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

/**
 * A {@code JLabel} that mimics the aspect of a {@link JTable}'s header cell.
 */
public class TableHeaderCell extends JLabel {
  /**
   * Creates a new {@code TableHeaderCell}
   * 
   * @param table The table to copy the style from.
   * @param content The content to render inside the label.
   */
  public TableHeaderCell(JTable table, String content) {
    setText(content);
    setOpaque(true);
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    setHorizontalAlignment(CENTER);
    copyHeaderStyles(table.getTableHeader());
  }

  private void copyHeaderStyles(JTableHeader header) {
    setForeground(header.getForeground());
    setBackground(header.getBackground());
    setFont(header.getFont());
  }
}
