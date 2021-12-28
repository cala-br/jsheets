package com.jsheets.util;

public interface Action<T> {
  public void invoke(T a);
}
