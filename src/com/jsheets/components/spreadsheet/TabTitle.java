package com.jsheets.components.spreadsheet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * The text to display inside a {@link CloseableTab}.
 */
public class TabTitle extends JLabel {
  private final Spreadsheet spreadsheet;
  private final CloseableTab tab;

  /**
   * Creates a new {@code TabTitle}.
   * It synchronizes with the existing title.
   * @param spreadsheet The sheet that will contain this title's tab.
   * @param tab The tab that will contain this title.
   */
  public TabTitle(Spreadsheet spreadsheet, CloseableTab tab) {
    super();
    this.spreadsheet = spreadsheet;
    this.tab = tab;

    setBorder(
      BorderFactory.createEmptyBorder(0, 0, 0, 5)
    );
  }

  @Override
  public String getText() {
    if (spreadsheet == null)
      return null;

    int i = spreadsheet.indexOfTabComponent(tab);
    return i != -1
      ? spreadsheet.getTitleAt(i)
      : null;
  }
}
