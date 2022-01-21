package com.jsheets.cells;

import java.util.stream.Stream;

/**
 * Represents a view on a matrix of cells.
 */
public class CellView {
  private final Cell<?>[][] cells;

  /**
   * Creates a new {@code CellView} from the given
   * matrix of {@code Cells}
   */
  public CellView(Cell<?>[][] cells) {
    this.cells = cells;
  }

  /**
   * Returns the cell at the given position.
   * @param pos The position of the cell.
   * @throws IndexOutOfBoundsException
   *  The given positon was not within the matrix.
   */
  public Cell<?> get(CellPosition pos) {
    return get(pos.row, pos.col);
  }

  /**
   * Returns the cell at the given position.
   * @param row The row of the cell.
   * @param col The column of the cell.
   * @throws IndexOutOfBoundsException
   *  The given positon was not within the matrix.
   */
  public Cell<?> get(int row, int col) {
    return cells[row][col];
  }

  /**
   * Maps this view to an array of {@link SerializableCell}s.
   * @return
   *  A new array of serializable cells.
   */
  public SerializableCell[] toSerializableArray() {
    return getAllWithValue()
      .map(c -> SerializableCell.from(c))
      .toArray(SerializableCell[]::new);
  }

  /**
   * Streams only the cells that have a value.
   * That is, their {@code hasValue} property
   * is {@code true}.
   */
  public Stream<Cell<?>> getAllWithValue() {
    return Stream
      .of(cells)
      .flatMap(c -> Stream.of(c))
      .filter(c -> c != null)
      .filter(c -> c.hasValue());
  }
}
