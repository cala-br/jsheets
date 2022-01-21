package com.jsheets.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a span of cells.
 */
public class CellSpan extends ArrayList<Cell<?>> {
  /**
   * Returns a new list of only the cells that
   * contain a value.
   * <br>
   * That is, their {@code hasValue} property must yield {@code true}.
   */
  public List<Cell<?>> onlyWithValue() {
    return this
      .stream()
      .filter(c -> c.hasValue())
      .collect(Collectors.toList());
  }

  /**
   * Returns the cell at the given position,
   * or an empty optional if nothing is found.
   * @param row The row of the cell.
   * @param col The column of the cell.
   */
  public Optional<Cell<?>> get(int row, int col) {
    return this
      .stream()
      .filter(c -> c.isAt(row, col))
      .findFirst();
  }
}
