package com.jsheets.expressions.types;

import com.jsheets.expressions.Expression;

/**
 * An binary comparison between two expressions.
 * @param <T1> The input type of the first expression.
 * @param <T2> The input type of the second expression.
 */
public abstract class ComparisonExpression<T1, T2, R> implements BooleanExpression<Object> {
  private final Expression<T1, R> left;
  private final Expression<T2, R> right;

  public R computeLeft() {
    return left.compute();
  }

  public R computeRight() {
    return right.compute();
  }

  public ComparisonExpression(
    Expression<T1, R> left,
    Expression<T2, R> right
  ) {
    this.left = left;
    this.right = right;
  }
}
