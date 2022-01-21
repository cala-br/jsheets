package com.jsheets.components.dialogs;

import java.io.File;

import javax.swing.JOptionPane;

/**
 * A dialog that asks the user if he wants to overwrite
 * an existing file or not.
 */
public class FileAlreadyExistsDialog {
  /**
   * If the given file already exists, a popup will
   * ask the user if he wants to overwrite the existing file or not.
   * 
   * @param file The file that could be overwritten.
   * @return
   *  {@code true} if the given file can be saved or overwritten,
   *  {@code false} otherwise.
   */
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
