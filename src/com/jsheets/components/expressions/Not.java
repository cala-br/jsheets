package com.jsheets.components.expressions;

public class Not<T> extends UnaryExpression<T, Boolean> {
  public Not(Expression<T, Boolean> expression) {
    super(expression);
  }

  @Override
  public Boolean compute() {
    return !super.compute();
  }
}
