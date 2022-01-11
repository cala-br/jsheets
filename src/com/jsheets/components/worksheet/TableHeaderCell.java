package com.jsheets.components.worksheet;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public class TableHeaderCell extends JLabel {
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
