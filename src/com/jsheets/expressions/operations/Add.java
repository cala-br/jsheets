package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.BinaryExpression;

public class Add<T1, T2> extends BinaryExpression<T1, T2, Number> {
  public Add(Expression<T1, Number> left, Expression<T2, Number> right) {
    super(left, right);
  }

  @Override
  public Number compute() {
    return computeLeft().doubleValue() + computeRight().doubleValue();
  }
}
