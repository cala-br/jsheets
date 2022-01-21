package com.jsheets.components.dialogs;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Represents the result of a save operation.
 */
public enum SaveDialogResult {
  SAVED,
  NOT_SAVED,
  CANCELED;

  /**
   * Creates a {@code SaveDialogResult} from a {@link JFileChooser}
   * result.
   * @param o The option to convert.
   * @return A {@code SaveDialogResult}.
   */
  public static SaveDialogResult fromJChooserOption(int o) {
    switch(o) {
      case JFileChooser.APPROVE_OPTION: return SAVED;
      case JFileChooser.CANCEL_OPTION: return CANCELED;
      default: return NOT_SAVED;
    }
  }

  /**
   * Creates a {@code SaveDialogResult} from a {@link JOptionPane}
   * result.
   * @param o The option to convert.
   * @return A {@code SaveDialogResult}.
   */
  public static SaveDialogResult fromJOption(int o) {
    switch(o) {
      case JOptionPane.OK_OPTION: return SAVED;
      case JOptionPane.CANCEL_OPTION: return CANCELED;
      default: return NOT_SAVED;
    }
  }
}
