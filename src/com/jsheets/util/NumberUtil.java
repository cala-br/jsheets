package com.jsheets.util;

public class NumberUtil {
  public static boolean isNumber(String raw) {
    return raw.matches("^[0-9]*\\.?[0-9]+$");
  }
}
