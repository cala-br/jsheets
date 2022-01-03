package com.jsheets.components.cells;

public class TextCell extends Cell<String> {
  public TextCell(CellParams params) {
    super(params);
  }

  @Override
  protected String parse(String expression) {
    return expression;
  }
}
