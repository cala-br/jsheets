package com.jsheets.components.cells;

import com.jsheets.exceptions.ParseException;

public abstract class Cell<T> {
  private final int row;
  private final int column;
  private final CellView view;
  private String expression;
  private T value;


  protected CellView getCellView() {
    return view;
  }

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


  protected Cell(CellParams params) {
    this(params.expression, params.row, params.column, params.view);
  }

  protected Cell(String expression, int row, int col, CellView view) {
    this.row = row;
    this.column = col;
    this.view = view;
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

  public static int[] parsePosition(String position) {
    final var col = position.charAt(0) - 65;
    final var row = Integer.parseInt(position.substring(1)) - 1;

    return new int[] { row, col };
  }
}
