package com.jsheets.util;

public class NumberUtil {
  public static boolean isNumber(String raw) {
    return raw
      .chars()
      .allMatch(c -> (
        Character.isDigit(c) || c == '.'
      ));
  }
}
