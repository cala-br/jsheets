package com.jsheets.components.cells;

import java.io.Serializable;

public class SerializableCell implements Serializable {
  public final CellPosition position;
  public final String expression;

  public SerializableCell(CellPosition position, String expression) {
    this.position = position;
    this.expression = expression;
  }

  public static SerializableCell from(Cell<?> cell) {
    return new SerializableCell(
      cell.getPosition(),
      cell.getExpression()
    );
  }
}
