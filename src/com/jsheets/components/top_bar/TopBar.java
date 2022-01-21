package com.jsheets.components.top_bar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * A menu bar that displays open and save actions.
 */
public class TopBar extends JMenuBar {
  public TopBar() {
    super();

    add(createFileMenu());
  }

  private JMenu createFileMenu() {
    return new MenuTab("File",
      new SaveItem(),
      new OpenFileItem()
    );
  }
}
