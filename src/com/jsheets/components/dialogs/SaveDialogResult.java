package com.jsheets.components.dialogs;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public enum SaveDialogResult {
  SAVED,
  NOT_SAVED,
  CANCELED;

  public static SaveDialogResult fromJChooserOption(int o) {
    switch(o) {
      case JFileChooser.APPROVE_OPTION: return SAVED;
      case JFileChooser.CANCEL_OPTION: return CANCELED;
      default: return NOT_SAVED;
    }
  }

  public static SaveDialogResult fromJOption(int o) {
    switch(o) {
      case JOptionPane.OK_OPTION: return SAVED;
      case JOptionPane.CANCEL_OPTION: return CANCELED;
      default: return NOT_SAVED;
    }
  }
}
