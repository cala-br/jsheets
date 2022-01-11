package com.jsheets.components.misc;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jsheets.util.ActionEvent;

public class EnterKeyAdapter extends KeyAdapter {
  public final ActionEvent onEnterPressed = new ActionEvent();

  public EnterKeyAdapter() {
    super();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      onEnterPressed.fire();
    }
  }
}