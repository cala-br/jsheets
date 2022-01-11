package com.jsheets.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CellSpan extends ArrayList<Cell<?>> {
  public List<Cell<?>> onlyWithValue() {
    return this
      .stream()
      .filter(c -> c.hasValue())
      .collect(Collectors.toList());
  }

  public Optional<Cell<?>> get(int row, int col) {
    return this
      .stream()
      .filter(c -> c.isAt(row, col))
      .findFirst();
  }
}
