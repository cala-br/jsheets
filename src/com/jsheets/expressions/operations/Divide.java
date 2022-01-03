package com.jsheets.expressions.operations;

import com.jsheets.expressions.BinaryExpression;
import com.jsheets.expressions.Expression;

public class Divide<T> extends BinaryExpression<T, Number> {
  public Divide(Expression<T, Number> left, Expression<T, Number> right) {
    super(left, right);
  }

  @Override
  public Number compute() {
    return computeLeft().doubleValue() / computeRight().doubleValue();
  }
}
