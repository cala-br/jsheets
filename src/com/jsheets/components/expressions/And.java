package com.jsheets.components.expressions;

public class And<T> extends BinaryExpression<T, Boolean> {
  public And(Expression<T, Boolean> left, Expression<T, Boolean> right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft() && computeRight();
  }
  
}
