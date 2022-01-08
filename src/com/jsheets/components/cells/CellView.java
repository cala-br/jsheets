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
    return getAllWithValue()
      .map(c -> SerializableCell.from(c))
      .toArray(SerializableCell[]::new);
  }

  public Stream<Cell<?>> getAllWithValue() {
    return Stream
      .of(cells)
      .flatMap(c -> Stream.of(c))
      .filter(c -> c != null)
      .filter(c -> c.hasValue());
  }
}
