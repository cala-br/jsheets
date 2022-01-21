package com.jsheets.util;

public class StringUtil {
  /**
   * Returns either the provided object casted
   * using {@code toString} or an empty string
   * if the object is {@code null}.
   * 
   * @param o The possibly {@code null} object.
   */
  public static String emptyIfNull(Object o) {
    return o == null ? "" : o.toString();
  }

  /**
   * Tells wether the provided string is {@code null} or
   * an empty string ({@code ""}).
   * 
   * @param s The target string.
   */
  public static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }

  /**
   * Tells wether the provided string is {@code null} or
   * contains only zero or more empty spaces.
   * 
   * @param s The target string.
   * @return
   *  {@code true} if the string is {@code null} or
   *  its {@code isBlank} method returns {@code true},
   *  {@code false} otherwise.
   */
  public static boolean isNullOrWhiteSpace(String s) {
    return s == null || s.isBlank();
  }
}
