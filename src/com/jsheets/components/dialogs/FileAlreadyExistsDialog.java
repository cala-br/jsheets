package com.jsheets.components.dialogs;

import java.io.File;

import javax.swing.JOptionPane;

public class FileAlreadyExistsDialog {
  public static boolean canSave(File file) {
    if (!file.exists()) {
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
