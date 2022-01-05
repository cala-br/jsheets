package com.jsheets.util;

public class BooleanUtil {
  public static boolean isBoolean(String raw) {
    return switch (raw) {
      case "true", "false" -> true;
      default -> false;
    };
  }
}
