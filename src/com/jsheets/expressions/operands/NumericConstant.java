package com.jsheets.expressions.operands;

import com.jsheets.expressions.types.ConstantExpression;
import com.jsheets.expressions.types.NumericExpression;

public class NumericConstant extends ConstantExpression<Number> implements NumericExpression<Number> {
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