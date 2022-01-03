package com.jsheets.expressions;

public abstract class UnaryExpression<T, R> implements Expression<T, R> {
  private final Expression<T, R> expression;

  public UnaryExpression(Expression<T, R> expression) {
    this.expression = expression;
  }

  @Override
  public R compute() {
    return expression.compute();
  }
}
