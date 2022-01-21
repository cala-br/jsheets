package com.jsheets.cells;

import java.io.Serializable;

/**
 * A serializable representation of a cell.
 */
public class SerializableCell implements Serializable {
  private static final long serialVersionUID = 1L;
  private final String position;
  private final String expression;

  /**
   * Creates a new {@code SerializableCell} from the given
   * position and expression.
   * @param positon The position of the cell.
   * @param expression The expression of the cell.
   */
  public SerializableCell(CellPosition position, String expression) {
    this.position = position.toString();
    this.expression = expression;
  }


  /**
   * @return
   *  The position of the corresponding {@link Cell}.
   */
  public CellPosition getPosition() {
    return CellPosition.from(position);
  }

  /**
   * @return
   *  The expression of the corresponding {@link Cell}.
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Converts an existing cell to a serializable one.
   * @param cell The cell to convert.
   * @return
   *  A new {@code SerializableCell} representing the given {@code Cell}.
   */
  public static SerializableCell from(Cell<?> cell) {
    return new SerializableCell(
      cell.getPosition(),
      cell.getExpression()
    );
  }
}
