package com.jsheets.util;

public class Value {
  /**
   * @param <T> The type of the value.
   * @param value The possibly null value.
   * @param or A replacement for {@code value} if it is {@code null}.
   * @return
   *  {@code value} if not {@code null}, {@code or} otherwise.
   */
  public static <T> T coalesce(T value, T or) {
    return value != null
      ? value
      : or;
  }
}
