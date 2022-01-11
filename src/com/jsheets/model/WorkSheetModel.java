package com.jsheets.model;

import javax.swing.table.AbstractTableModel;

import com.jsheets.cells.Cell;
import com.jsheets.cells.CellFactory;
import com.jsheets.cells.CellParams;
import com.jsheets.cells.CellView;
import com.jsheets.cells.ErrorCell;
import com.jsheets.cells.ExpressionCell;
import com.jsheets.util.Event;
import com.jsheets.util.StringUtil;

public class WorkSheetModel extends AbstractTableModel {
  private final static int columns = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".length();
  private final static int rows = 100;

  public final Event<Cell<?>> onCellUpdated = new Event<>();
  private final Cell<?>[][] cells = new Cell<?>[rows][columns];

  public CellView getView() {
    return new CellView(cells);
  }


  public Cell<?> getCellAt(int row, int col) {
    var cell = cells[row][col];
    if (cell == null) {
      cell = createCell("", row, col);
    }

    return cell;
  }


  @Override
  public boolean isCellEditable(int row, int col) {
    return true;
  }

  @Override
  public int getRowCount() {
    return rows;
  }

  @Override
  public int getColumnCount() {
    return columns;
  }

  @Override
  public Object getValueAt(int row, int col) {
    return getCellAt(row, col).getValue();
  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    final var cell = createCellAt(value, row, col);
    recomputeExpressions(cell);
  }

  private void recomputeExpressions(Cell<?> excluded) {
    getView()
      .getAllWithValue()
      .filter(c -> (
        c instanceof ExpressionCell ||
        c instanceof ErrorCell
      ))
      .filter(c -> c != excluded)
      .forEach(c -> {
        recreateCell(c);
      });
  }

  private Cell<?> recreateCell(Cell<?> cell) {
    final var pos = cell.getPosition();
    return createCellAt(
      cell.getExpression(), pos.row, pos.col
    );
  }

  private Cell<?> createCellAt(Object value, int row, int col) {
    final var expression = StringUtil.emptyIfNull(value);
    final var cell = createCell(expression, row, col);
    cells[row][col] = cell;

    onCellUpdated.fire(cell);
    fireTableCellUpdated(row, col);
    return cell;
  }


  private Cell<?> createCell(String expression, int row, int col) {
    return CellFactory.create(
      new CellParams(expression, row, col, getView())
    );
  }
}
