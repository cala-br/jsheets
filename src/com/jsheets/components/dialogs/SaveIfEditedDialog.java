package com.jsheets.components.dialogs;

import javax.swing.JOptionPane;

public class SaveIfEditedDialog {
  public static int canSave() {
    return JOptionPane.showConfirmDialog(
      null,
      "The worksheet has been modified, do you want to save it?",
      "Exit without save",
      JOptionPane.YES_NO_CANCEL_OPTION
    );
  }
}
