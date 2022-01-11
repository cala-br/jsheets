package com.jsheets.components.misc;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jsheets.events.ActionEvent;
import com.jsheets.events.EventArgs;

public class EnterKeyAdapter extends KeyAdapter {
  public final ActionEvent<EnterKeyAdapter> onEnterPressed = new ActionEvent<>();

  public EnterKeyAdapter() {
    super();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      onEnterPressed.fire(
        new EventArgs<>(this)
      );
    }
  }
}