package com.jsheets.expressions;

public class LessThan extends ComparisonExpression<Number, Number> {
  public LessThan(NumericExpression left, NumericExpression right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft().doubleValue() < computeRight().doubleValue();
  }
}
