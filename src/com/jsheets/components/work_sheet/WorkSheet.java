package com.jsheets.components.work_sheet;

import java.util.function.Consumer;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

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

  private void initSelectionModel() {
    final var model = getSelectionModel();
    model.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    model.addListSelectionListener(this::handleCellSelection);
    setCellSelectionEnabled(true);
  }

  private void handleCellSelection(ListSelectionEvent e) {
    final var rows = getSelectedRows();
    final var cols = getSelectedColumns();
    final var data = getCellView(rows, cols);

    onCellSelected.accept(
      new CellSelectionEvent(data, rows, cols)
    );
  }

  private CellList getCellView(int[] rows, int[] cols) {
    final var data = new CellList();
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
