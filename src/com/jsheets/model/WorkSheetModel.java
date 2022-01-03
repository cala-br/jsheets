package com.jsheets.model;

import javax.swing.table.AbstractTableModel;

import com.jsheets.components.cells.Cell;
import com.jsheets.components.cells.CellFactory;
import com.jsheets.components.cells.CellParams;
import com.jsheets.components.cells.CellView;
import com.jsheets.util.StringUtil;

public class WorkSheetModel extends AbstractTableModel {
  private final static int columns = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".length();
  private final static int rows = 100;

  private final Cell<?>[][] cells;

  public CellView getView() {
    return new CellView(cells);
  }


  public WorkSheetModel() {
    super();
    cells = new Cell<?>[rows][columns];
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
    return cells.length;
  }

  @Override
  public int getColumnCount() {
    return cells[0].length;
  }

  @Override
  public Object getValueAt(int row, int col) {
    return getCellAt(row, col).getValue();
  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    final var expression = StringUtil.emptyIfNull(value);
    cells[row][col] = createCell(expression, row, col);
  }

  private Cell<?> createCell(String expression, int row, int col) {
    return CellFactory.create(
      new CellParams(expression, row, col, getView())
    );
  }
}
