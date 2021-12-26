package com.jsheets.components.top_bar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopBar extends JMenuBar {
  public TopBar() {
    super();

    add(createFileMenu());
    add(createEditMenu());
  }

  private JMenu createFileMenu() {
    return new MenuTab("File",
      new JMenuItem("Save"),
      new JMenuItem("Open")
    );
  }

  private JMenu createEditMenu() {
    return new MenuTab("Edit",
      new JMenuItem("Cut"),
      new JMenuItem("Copy"),
      new JMenuItem("Paste")
    );
  }
}
