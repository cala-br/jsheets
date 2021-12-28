package com.jsheets.util;

public class Value {
  public static <T> T coalesce(T value, T or) {
    return value != null
      ? value
      : or;
  }
}
