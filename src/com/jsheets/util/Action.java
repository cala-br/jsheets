package com.jsheets.util;

/**
 * A funcional interface that takes no arguments
 * and returns no value.
 */
@FunctionalInterface
public interface Action {
  public void invoke();
}
