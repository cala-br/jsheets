package com.jsheets.expressions.operands;

import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.types.ConstantExpression;
import com.jsheets.expressions.types.NumericExpression;

public class NumericConstant extends ConstantExpression<Number> implements NumericExpression<Number> {
  public NumericConstant(Number value) {
    super(value);
  }

  public static NumericConstant parse(Object constant) throws ParseException {
    try {
      return new NumericConstant(
        Double.parseDouble(
          String.valueOf(constant)
        )
      );
    }
    catch (NumberFormatException e) {
      throw new ParseException();
    }
  }
}