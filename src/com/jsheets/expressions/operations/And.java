package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.types.BinaryExpression;
import com.jsheets.expressions.types.BooleanExpression;

public class And<T1, T2> extends BinaryExpression<T1, T2, Boolean> implements BooleanExpression<Object> {
  public And(Expression<T1, Boolean> left, Expression<T2, Boolean> right) {
    super(left, right);
  }

  @Override
  public Boolean compute() {
    return computeLeft() && computeRight();
  }
  
}
