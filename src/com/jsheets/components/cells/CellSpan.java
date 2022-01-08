package com.jsheets.components.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CellSpan extends ArrayList<Cell<?>> {
  public List<Cell<?>> onlyWithValue() {
    return this
      .stream()
      .filter(c -> c.hasValue())
      .toList();
  }

  public Optional<Cell<?>> get(int row, int col) {
    return this
      .stream()
      .filter(c -> c.isAt(row, col))
      .findFirst();
  }
}
