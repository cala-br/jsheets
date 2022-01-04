package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.ComparisonExpression;

public class GreaterThan<T1, T2> extends ComparisonExpression<T1, T2, Number> {
  public GreaterThan(Expression<T1, Number> left, Expression<T2, Number> right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft().doubleValue() > computeRight().doubleValue();
  }
}
