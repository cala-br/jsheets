package com.jsheets.util;

public class BooleanUtil {
  public static boolean isBoolean(String raw) {
    switch (raw) {
      case "true":
      case "false": return true;
      default: return false;
    }
  }
}
