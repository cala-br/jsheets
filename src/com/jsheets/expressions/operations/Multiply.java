package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.BinaryExpression;

public class Multiply<T1, T2> extends BinaryExpression<T1, T2, Number> {
  public Multiply(Expression<T1, Number> left, Expression<T2, Number> right) {
    super(left, right);
  }

  @Override
  public Number compute() {
    return computeLeft().doubleValue() * computeRight().doubleValue();
  }
}
