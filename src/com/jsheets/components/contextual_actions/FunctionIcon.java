package com.jsheets.components.contextual_actions;

import javax.swing.JLabel;

import com.jsheets.services.FontService;

public class FunctionIcon extends JLabel {
  public FunctionIcon() {
    super();
    setText("\uE945");
    setFont(FontService.getInstance().getSegoeMdl2Assets());
  }
}
