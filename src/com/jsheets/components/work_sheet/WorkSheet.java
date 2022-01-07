package com.jsheets.components.work_sheet;

import java.util.function.Consumer;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import com.jsheets.components.cells.CellView;
import com.jsheets.components.cells.SerializableCell;
import com.jsheets.model.WorkSheetModel;

public class WorkSheet extends JTable {
  private final Consumer<CellSelectionEvent> onCellSelected;
  private final WorkSheetModel model;

  public WorkSheet(Consumer<CellSelectionEvent> onCellSelected) {
    super(new WorkSheetModel());
    this.onCellSelected = onCellSelected;
    this.model = (WorkSheetModel)getModel();

    initSelectionModel();
  }


  public CellView getCellView() {
    return model.getView();
  }


  public void loadSerializedCells(SerializableCell[] cells) {
    for (final var cell : cells) {
      final var expr = cell.getExpression();
      final var pos = cell.getPosition();

      model.setValueAt(expr, pos.row, pos.col);
    }
  }


  private void initSelectionModel() {
    final var model = getSelectionModel();
    model.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    model.addListSelectionListener(this::handleCellSelection);
    setCellSelectionEnabled(true);
  }

  private void handleCellSelection(ListSelectionEvent e) {
    final var rows = getSelectedRows();
    final var cols = getSelectedColumns();
    final var data = getSelectedCells(rows, cols);

    onCellSelected.accept(
      new CellSelectionEvent(data, rows, cols)
    );
  }

  private CellSpan getSelectedCells(int[] rows, int[] cols) {
    final var data = new CellSpan();
    for (var row : rows) {
      for (var col : cols) {
        data.add(
          model.getCellAt(row, col)
        );
      }
    }

    return data;
  }
}
