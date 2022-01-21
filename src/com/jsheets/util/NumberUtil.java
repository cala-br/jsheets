package com.jsheets.util;

public class NumberUtil {
  /**
   * Tells wether the given string is a number or not.
   * @return
   *  {@code true} if the string is a number, {@code false} otherwise.
   */
  public static boolean isNumber(String raw) {
    return raw.matches("^[0-9]*\\.?[0-9]+$");
  }
}
