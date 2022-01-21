package com.jsheets.expressions.types;

import com.jsheets.expressions.Expression;

/**
 * An expression that takes a value and simply returns it.
 * Used for operators.
 */
public class ConstantExpression<T> implements Expression<T, T> {
  private final T value;

  public ConstantExpression(T value) {
    this.value = value;
  }

  @Override
  public T compute() {
    return value;
  }
}
