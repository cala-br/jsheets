package com.jsheets.components.cells;

import java.util.stream.Stream;

public class CellView {
  private final Cell<?>[][] cells;

  public CellView(Cell<?>[][] cells) {
    this.cells = cells;
  }

  public Cell<?> get(CellPosition pos) {
    return get(pos.row, pos.col);
  }

  public Cell<?> get(int row, int col) {
    return cells[row][col];
  }

  public SerializableCell[] toSerializableArray() {
    return Stream
      .of(cells)
      .flatMap(c -> Stream.of(c))
      .filter(c -> c != null)
      .filter(c -> c.hasValue())
      .map(c -> SerializableCell.from(c))
      .toArray(SerializableCell[]::new);
  }
}
