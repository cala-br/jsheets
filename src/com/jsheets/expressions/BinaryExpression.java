package com.jsheets.expressions;

public abstract class BinaryExpression<T, R> implements Expression<T, R> {
  private final Expression<T, R> left;
  private final Expression<T, R> right;

  public R computeLeft() {
    return left.compute();
  }

  public R computeRight() {
    return right.compute();
  }

  public BinaryExpression(Expression<T, R> left, Expression<T, R> right) {
    this.left = left;
    this.right = right;
  }
}
