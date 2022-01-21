package com.jsheets.expressions;

/**
 * Represents an expression that takes a value and
 * computes it, returning nanother value.
 * @param <T> The input type.
 * @param <R> The result type.
 */
public interface Expression<T, R> {
  public R compute();
}
