package com.jsheets.expressions.operations;

import com.jsheets.expressions.Expression;
import com.jsheets.expressions.UnaryExpression;

public class Parentheses<T, R> extends UnaryExpression<T, R> {
  public Parentheses(Expression<T, R> expression) {
    super(expression);
  }
}
