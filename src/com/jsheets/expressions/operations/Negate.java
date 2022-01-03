package com.jsheets.expressions.operations;

import com.jsheets.expressions.NumericExpression;
import com.jsheets.expressions.UnaryExpression;

public class Negate extends UnaryExpression<Number, Number> {
  public Negate(NumericExpression expression) {
    super(expression);
  }

  @Override
  public Number compute() {
    return -(super.compute().doubleValue());
  }
}
