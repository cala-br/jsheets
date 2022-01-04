package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.BinaryExpression;

public class Or<T1, T2> extends BinaryExpression<T1, T2, Boolean> {
  public Or(Expression<T1, Boolean> left, Expression<T2, Boolean> right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft() || computeRight();
  }
}
