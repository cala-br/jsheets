package com.jsheets.cells;

import com.jsheets.exceptions.ParseException;

public abstract class Cell<T> {
  private final CellPosition position;
  private final CellView view;
  private String expression;
  private T value;


  public CellPosition getPosition() {
    return position;
  }

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
    this(params.expression,new CellPosition(params.row, params.column), params.view);
  }

  protected Cell(String expression, CellPosition position, CellView view) {
    this.position = position;
    this.view = view;
    applyExpression(expression);
  }


  public void reapplyExpression() {
    applyExpression(getExpression());
  }

  public void applyExpression(String expression) {
    setExpression(expression);
    setValue(parse(expression));
  }

  protected abstract T parse(String expression) throws ParseException;


  @Override
  public String toString() {
    return String.format("%s - '%s'", getPosition(), getExpression());
  }

  public boolean hasValue() {
    final var value = getValue();
    return (
      value != null && !String.valueOf(value).isEmpty()
    );
  }

  public boolean isAt(int row, int column) {
    return getPosition().isAt(row, column);
  }
}
