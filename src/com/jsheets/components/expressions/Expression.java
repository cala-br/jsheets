package com.jsheets.components.expressions;

public class Expression<T> {
  private T left;
  private Expression<T> right;
  private Expression<T> parent;
}
