package com.jsheets.expressions;

public class Parentheses<T, R> extends UnaryExpression<T, R> {
  public Parentheses(Expression<T, R> expression) {
    super(expression);
  }
}
