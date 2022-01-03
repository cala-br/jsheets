package com.jsheets.expressions;

public class Or<T> extends BinaryExpression<T, Boolean> {
  public Or(Expression<T, Boolean> left, Expression<T, Boolean> right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft() || computeRight();
  }
}
