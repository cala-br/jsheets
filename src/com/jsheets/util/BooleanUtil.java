package com.jsheets.util;

public class BooleanUtil {
  /**
   * Tells wether a string matches {@code "true"} or {@code "false"}.
   * @param raw The string to be checked.
   * @return
   *  {@code true} if the string represents a boolean, {@code false otherwise}.
   *  Keep in mind that {@code isBoolean("false")} will return {@code true}.
   */
  public static boolean isBoolean(String raw) {
    switch (raw) {
      case "true":
      case "false": return true;
      default: return false;
    }
  }
}
