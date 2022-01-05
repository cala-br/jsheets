package com.jsheets.expressions.types;

import com.jsheets.expressions.Expression;

public abstract class BinaryExpression<T1, T2, R> implements Expression<Object, R> {
  private final Expression<T1, R> left;
  private final Expression<T2, R> right;

  public R computeLeft() {
    return left.compute();
  }

  public R computeRight() {
    return right.compute();
  }

  public BinaryExpression(Expression<T1, R> left, Expression<T2, R> right) {
    this.left = left;
    this.right = right;
  }
}
