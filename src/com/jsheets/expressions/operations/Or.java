package com.jsheets.expressions.operations;

import com.jsheets.expressions.BinaryExpression;
import com.jsheets.expressions.Expression;

public class Or<T> extends BinaryExpression<T, Boolean> {
  public Or(Expression<T, Boolean> left, Expression<T, Boolean> right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft() || computeRight();
  }
}
