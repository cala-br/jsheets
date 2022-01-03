package com.jsheets.components.expressions;

public class Add<T> extends BinaryExpression<T, Number> {
  public Add(Expression<T, Number> left, Expression<T, Number> right) {
    super(left, right);
  }

  @Override
  public Number compute() {
    return computeLeft().doubleValue() + computeRight().doubleValue();
  }
}
