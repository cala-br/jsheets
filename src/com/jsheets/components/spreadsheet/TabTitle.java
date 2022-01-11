package com.jsheets.components.spreadsheet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class TabTitle extends JLabel {
  private final Spreadsheet spreadsheet;
  private final CloseableTab tab;

  public TabTitle(Spreadsheet spreadsheet, CloseableTab tab) {
    super();
    this.spreadsheet = spreadsheet;
    this.tab = tab;

    setBorder(
      BorderFactory.createEmptyBorder(0, 0, 0, 5)
    );
  }

  public String getText() {
    if (spreadsheet == null)
      return null;

    int i = spreadsheet.indexOfTabComponent(tab);
    return i != -1
      ? spreadsheet.getTitleAt(i)
      : null;
  }
}
