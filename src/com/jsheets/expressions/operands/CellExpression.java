package com.jsheets.expressions.operands;

import com.jsheets.components.cells.Cell;
import com.jsheets.expressions.Expression;

public class CellExpression implements Expression<Cell<?>, Number> {
  private final Cell<?> cell;

  public CellExpression(Cell<?> cell) {
    this.cell = cell;
  }

  @Override
  public Number compute() {
    return NumericConstant
      .parse(cell.getValue())
      .compute();
  }
}
