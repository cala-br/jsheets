package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.UnaryExpression;

public class Not<T> extends UnaryExpression<T, Boolean> {
  public Not(Expression<T, Boolean> expression) {
    super(expression);
  }

  @Override
  public Boolean compute() {
    return !super.compute();
  }
}
