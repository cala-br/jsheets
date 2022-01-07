package com.jsheets.components.top_bar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.jsheets.components.icons.CopyIcon;
import com.jsheets.components.icons.CutIcon;
import com.jsheets.components.icons.PasteIcon;

public class TopBar extends JMenuBar {
  public TopBar() {
    super();

    add(createFileMenu());
    add(createEditMenu());
  }

  private JMenu createFileMenu() {
    return new MenuTab("File",
      new SaveItem(),
      new OpenFileItem()
    );
  }

  private JMenu createEditMenu() {
    return new MenuTab("Edit",
      new JMenuItem("Cut", new CutIcon()),
      new JMenuItem("Copy", new CopyIcon()),
      new JMenuItem("Paste", new PasteIcon())
    );
  }
}
