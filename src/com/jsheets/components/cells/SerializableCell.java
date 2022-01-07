package com.jsheets.components.cells;

import java.io.Serializable;

public class SerializableCell implements Serializable {
  private final String position;
  private final String expression;

  public SerializableCell(CellPosition position, String expression) {
    this.position = position.toString();
    this.expression = expression;
  }


  public CellPosition getPosition() {
    return CellPosition.from(position);
  }

  public String getExpression() {
    return expression;
  }

  public static SerializableCell from(Cell<?> cell) {
    return new SerializableCell(
      cell.getPosition(),
      cell.getExpression()
    );
  }
}
