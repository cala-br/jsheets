package com.jsheets.components.dialogs;

import javax.swing.JOptionPane;

import com.jsheets.services.storage.JSheetPath;

public class FileAlreadyExistsDialog {
  public static boolean canFileBeSaved(JSheetPath path) {
    if (!path.exists()) {
      return true;
    }

    final var choice = JOptionPane.showConfirmDialog(
      null,
      "This file already exists, do you want to proceed?",
      "File already exists",
      JOptionPane.OK_CANCEL_OPTION
    );

    return choice == JOptionPane.OK_OPTION;
  }

}
