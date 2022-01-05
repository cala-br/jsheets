package com.jsheets.expressions.types;

import com.jsheets.expressions.Expression;

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
