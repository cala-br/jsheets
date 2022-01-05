package com.jsheets.expressions.operands;

import com.jsheets.components.cells.Cell;
import com.jsheets.expressions.types.NumericExpression;

public class CellExpression implements NumericExpression<Cell<?>> {
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
