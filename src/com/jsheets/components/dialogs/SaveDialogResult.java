package com.jsheets.components.dialogs;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public enum SaveDialogResult {
  SAVED,
  NOT_SAVED,
  CANCELED;

  public static SaveDialogResult fromJChooserOption(int o) {
    return switch(o) {
      case JFileChooser.APPROVE_OPTION -> SAVED;
      case JFileChooser.CANCEL_OPTION -> CANCELED;
      default -> NOT_SAVED;
    };
  }

  public static SaveDialogResult fromJOption(int o) {
    return switch(o) {
      case JOptionPane.OK_OPTION -> SAVED;
      case JOptionPane.CANCEL_OPTION -> CANCELED;
      default -> NOT_SAVED;
    };
  }
}
