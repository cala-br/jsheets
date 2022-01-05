package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.UnaryExpression;

public class Negate<T> extends UnaryExpression<T, Number> {
  public Negate(Expression<T, Number> expression) {
    super(expression);
  }

  @Override
  public Number compute() {
    return -(super.compute().doubleValue());
  }
}
