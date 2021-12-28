package com.jsheets.components.cells;

public class TextCell extends Cell<String> {
  public TextCell(int row, int col) {
    this("", row, col);
  }

  public TextCell(String expression, int row, int col) {
    super(expression, row, col);
  }

  @Override
  protected String parse(String expression) {
    return expression;
  }
}
