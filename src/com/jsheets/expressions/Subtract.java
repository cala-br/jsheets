package com.jsheets.expressions;

public class Subtract<T> extends BinaryExpression<T, Number> {
  public Subtract(Expression<T, Number> left, Expression<T, Number> right) {
    super(left, right);
  }

  @Override
  public Number compute() {
    return computeLeft().doubleValue() - computeRight().doubleValue();
  }
}
