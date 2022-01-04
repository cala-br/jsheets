package com.jsheets.expressions.operands;

import com.jsheets.expressions.types.ConstantExpression;

public class BooleanConstant extends ConstantExpression<Boolean> {
  public BooleanConstant(Boolean value) {
    super(value);
  }

  public static BooleanConstant parse(Object constant) {
    return new BooleanConstant(
      Boolean.parseBoolean(
        String.valueOf(constant)
      )
    );
  }
}
