package com.jsheets.components.expressions;

import com.jsheets.exceptions.ParseException;

public class ExpressionTree<T> {
  private Expression<T> root;

  private ExpressionTree() {

  }

  public static ExpressionTree<?> parse(String expression) {
    // TODO: implement
    throw new ParseException();
  }
}
