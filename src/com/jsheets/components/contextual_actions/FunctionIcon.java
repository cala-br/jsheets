package com.jsheets.components.contextual_actions;

import javax.swing.JLabel;

import com.jsheets.services.ServiceRepository;

public class FunctionIcon extends JLabel {
  public FunctionIcon() {
    super();
    setText("\uE945");
    setFont(ServiceRepository.fontService.getSegoeMdl2Assets());
  }
}
