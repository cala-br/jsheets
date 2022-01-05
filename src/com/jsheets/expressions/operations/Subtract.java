package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.BinaryExpression;
import com.jsheets.expressions.types.NumericExpression;

public class Subtract<T1, T2> extends BinaryExpression<T1, T2, Number> implements NumericExpression<Object> {
  public Subtract(Expression<T1, Number> left, Expression<T2, Number> right) {
    super(left, right);
  }

  @Override
  public Number compute() {
    return computeLeft().doubleValue() - computeRight().doubleValue();
  }
}
