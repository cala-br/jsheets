package com.jsheets.expressions;

public abstract class ComparisonExpression<T, R> implements BooleanExpression {
  private final Expression<T, R> left;
  private final Expression<T, R> right;

  public R computeLeft() {
    return left.compute();
  }

  public R computeRight() {
    return right.compute();
  }

  public ComparisonExpression(
    Expression<T, R> left,
    Expression<T, R> right
  ) {
    this.left = left;
    this.right = right;
  }
}
