package com.jsheets.components.expressions;

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
