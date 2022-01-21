package com.jsheets.components.spreadsheet;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * The close button to display inside a
 * {@link CloseableTab}
 */
public class TabButton extends JButton {
  public TabButton(Component content) {
    super();
    add(content);
    setPreferredSize(new Dimension(17, 17));
    setToolTipText("Close");
    setContentAreaFilled(false);
    setFocusable(false);
    setBorder(BorderFactory.createEtchedBorder());
    setBorderPainted(false);
    setRolloverEnabled(true);
  }
}
