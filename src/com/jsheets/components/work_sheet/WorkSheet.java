package com.jsheets.components.work_sheet;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import com.jsheets.model.WorkSheetModel;
import com.jsheets.util.Action;

public class WorkSheet extends JTable {
  private final Action<CellSelectionEvent> onCellSelected;
  private final WorkSheetModel model;

  public WorkSheet(Action<CellSelectionEvent> onCellSelected) {
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

    onCellSelected.invoke(
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
