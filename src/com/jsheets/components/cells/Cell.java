package com.jsheets.components.cells;

import com.jsheets.exceptions.ParseException;

public abstract class Cell<T> {
  private final int row;
  private final int column;
  private String expression;
  private T value;


  public String getExpression() {
    return expression;
  }

  public T getValue() {
    return value;
  }

  protected void setExpression(String expression) {
    this.expression = expression;
  }

  protected void setValue(T value) {
    this.value = value;
  }


  protected Cell(String expression, int row, int col) {
    this.row = row;
    this.column = col;
    applyExpression(expression);
  }

  public void applyExpression(String expression) {
    setExpression(expression);
    setValue(parse(expression));
  }

  protected abstract T parse(String expression) throws ParseException;


  @Override
  public String toString() {
    final var value = getExpression().toString();
    final var position = formatPosition(row, column);

    return String.format("%s - '%s'", position, value);
  }

  public boolean hasValue() {
    final var value = getValue();
    return (
      value != null && !String.valueOf(value).isEmpty()
    );
  }

  public boolean isAt(int row, int column) {
    return this.row == row && this.column == column;
  }

  public static String formatPosition(int row, int col) {
    final var colName = (char)(col + 65);
    final var rowName = Integer.toString(row + 1);

    return colName + rowName;
  }
}
