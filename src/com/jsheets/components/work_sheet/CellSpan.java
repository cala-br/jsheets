package com.jsheets.components.work_sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jsheets.components.cells.Cell;

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
