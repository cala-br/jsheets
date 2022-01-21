package com.jsheets.expressions.types;

import com.jsheets.expressions.Expression;

/**
 * An operation that takes only one expression as operand.
 * @param <T> The input type.
 * @param <R> The result type.
 */
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
