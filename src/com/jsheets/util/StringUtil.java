package com.jsheets.util;

public class StringUtil {
  public static String emptyIfNull(Object o) {
    return o == null ? "" : o.toString();
  }

  public static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }

  public static boolean isNullOrWhiteSpace(String s) {
    return s == null || s.isBlank();
  }
}
