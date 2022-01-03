package com.jsheets.components.expressions;

public class NumericConstant extends ConstantExpression<Number> {
  public NumericConstant(Number value) {
    super(value);
  }

  public static NumericConstant parse(Object constant) {
    return new NumericConstant(
      Double.parseDouble(
        String.valueOf(constant)
      )
    );
  }
}