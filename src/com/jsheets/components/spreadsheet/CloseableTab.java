package com.jsheets.components.spreadsheet;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.jsheets.components.icons.CloseIcon;

public class CloseableTab extends JPanel {
  private final TabButton closeButton = new TabButton(new CloseIcon());
  private final TabTitle title;

  public CloseableTab(Spreadsheet spreadsheet) {
    super();
    this.title = new TabTitle(spreadsheet, this);

    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    setOpaque(false);
    add(title);
    add(closeButton);
    setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
  }
}
