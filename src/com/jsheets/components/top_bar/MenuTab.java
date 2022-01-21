package com.jsheets.components.top_bar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Helper for creating {@code JMenus}
 */
public class MenuTab extends JMenu {
  public MenuTab(String title, JMenuItem...items) {
    super(title);

    for (var item : items) {
      add(item);
    }
  }
}
