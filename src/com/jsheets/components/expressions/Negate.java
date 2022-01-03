package com.jsheets.components.expressions;

public class Negate extends UnaryExpression<Number, Number> {
  public Negate(NumericExpression expression) {
    super(expression);
  }

  @Override
  public Number compute() {
    return -(super.compute().doubleValue());
  }
}
