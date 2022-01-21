package com.jsheets.cells;

import com.jsheets.exceptions.ParseException;

/**
 * Represents a cell inside a {@code Worksheet}.
 */
public abstract class Cell<T> {
  private final CellPosition position;
  private final CellView view;
  private String expression;
  private T value;


  /**
   * @return
   *  The position of the cell.
   */
  public CellPosition getPosition() {
    return position;
  }

  /**
   * @return
   *  A view of the whole matrix of cells.
   */
  protected CellView getCellView() {
    return view;
  }

  /**
   * @return
   *  The expression that created this cell.
   */
  public String getExpression() {
    return expression;
  }

  /**
   * @return
   *  The value rendered inside this cell.
   */
  public T getValue() {
    return value;
  }

  /**
   * Overrides the expression without recomputing it.
   * @param expression The new expression.
   */
  protected void setExpression(String expression) {
    this.expression = expression;
  }

  /**
   * Overrides the value.
   * @param value The new value.
   */
  protected void setValue(T value) {
    this.value = value;
  }


  /**
   * Creates a new {@code Cell} from the given parameters.
   */
  protected Cell(CellParams params) {
    this(params.expression, new CellPosition(params.row, params.column), params.view);
  }

  /**
   * Creates a new {@code Cell} from the given parameters.
   * @param expression The expression to compute.
   * @param position The position of the cell.
   * @param view The view of the cell matrix.
   */
  protected Cell(String expression, CellPosition position, CellView view) {
    this.position = position;
    this.view = view;
    applyExpression(expression);
  }


  /**
   * Re-computes the expression, updating the cell's value.
   */
  public void reapplyExpression() {
    applyExpression(getExpression());
  }

  /**
   * Applies the given expression, computing the cell value.
   * @param expression The expression to apply.
   */
  public void applyExpression(String expression) {
    setExpression(expression);
    setValue(parse(expression));
  }

  /**
   * Computes the expression, returning the value.
   * @param expression The expression to compute.
   * @return The value of the expression.
   */
  protected abstract T parse(String expression) throws ParseException;


  @Override
  public String toString() {
    return String.format("%s - '%s'", getPosition(), getExpression());
  }

  /**
   * Tells wether this cells contains a value or is
   * empty.
   * @return
   *  {@code true} if the cell's value is not an empty string
   *  and not null, {@code false} otherwise.
   */
  public boolean hasValue() {
    final var value = getValue();
    return (
      value != null && !String.valueOf(value).isEmpty()
    );
  }

  /**
   * Tells wether the cell is at the given position.
   * @param row The possible row of the cell.
   * @param column The possible column of the cell.
   * @return
   *  {@code true} if the cell is at the given position,
   *  {@code false} otherwise.
   */
  public boolean isAt(int row, int column) {
    return getPosition().isAt(row, column);
  }
}
