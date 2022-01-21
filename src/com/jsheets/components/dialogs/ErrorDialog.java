package com.jsheets.components.dialogs;

import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;

import com.jsheets.exceptions.ParseException;

/**
 * A dialog that displays an error message based on
 * a given exception.
 */
public class ErrorDialog {
  /**
   * Displays an error message from a given exception.
   * @param exception The exception to display.
   */
  public static void show(Exception exception) {
    JOptionPane.showMessageDialog(
      null, getExceptionMessage(exception), "Error", JOptionPane.ERROR_MESSAGE
    );
  }

  private static String getExceptionMessage(Exception exception) {
    final var messages = Map.of(
      IOException.class, "Error when opening file",
      ClassNotFoundException.class, "Data is corrupted",
      ParseException.class, "Parse error"
    );

    return messages
      .entrySet()
      .stream()
      .filter(e -> e.getKey().isInstance(exception))
      .map(e -> e.getValue())
      .findFirst()
      .orElse("An error has occurred");
  }
}
