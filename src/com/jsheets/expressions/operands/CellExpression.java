package com.jsheets.expressions.operands;

import com.jsheets.components.cells.Cell;
import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.types.NumericExpression;

public class CellExpression implements NumericExpression<Cell<?>> {
  private final Cell<?> cell;

  public CellExpression(Cell<?> cell) {
    this.cell = cell;
  }

  @Override
  public Number compute() {
    try {
      return NumericConstant
        .parse(cell.getValue())
        .compute();
    }
    catch (Exception e) {
      throw new ParseException();
    }
  }
}
