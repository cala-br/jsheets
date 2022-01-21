package com.jsheets.components.dialogs;

import javax.swing.JOptionPane;

import com.jsheets.components.worksheet.Worksheet;

/**
 * Asks the user if he wants to save an edited worksheet.
 */
public class SaveIfEditedDialog {
  /**
   * Asks the user if he wants to save the sheet only
   * if it has been edited.
   * @param worksheet The possibly edited worksheet.
   * @return The result of the operation.
   */
  public static SaveDialogResult canSave(Worksheet worksheet) {
    if (!worksheet.hasBeenEdited()) {
      return SaveDialogResult.SAVED;
    }

    final var result = JOptionPane.showConfirmDialog(
      null,
      "The worksheet has been modified, do you want to save it?",
      "Exit without save",
      JOptionPane.YES_NO_CANCEL_OPTION
    );

    return SaveDialogResult.fromJOption(result);
  }
}
