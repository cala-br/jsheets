package com.jsheets.components.worksheet;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import com.jsheets.components.cells.Cell;
import com.jsheets.components.cells.CellSelectionEvent;
import com.jsheets.components.cells.CellSpan;
import com.jsheets.components.cells.CellView;
import com.jsheets.components.cells.SerializableCell;
import com.jsheets.model.WorkSheetModel;
import com.jsheets.services.storage.JSheetFile;
import com.jsheets.util.Event;

public class Worksheet extends JTable {
  public final Event<CellSelectionEvent> onCellSelected = new Event<>();
  public final Event<Cell<?>> onCellEdited;
  private final WorkSheetModel model;

  private JSheetFile file = null;
  private boolean edited = false;
  public boolean hasBeenEdited() {
    return edited;
  }

  public JSheetFile getFile() {
    return file;
  }

  public void setFile(JSheetFile file) {
    this.file = file;
  }

  public CellView getCellView() {
    return model.getView();
  }


  public Worksheet(JSheetFile file, SerializableCell ...cells) {
    this(cells);
    setFile(file);
  }

  public Worksheet(SerializableCell ...cells) {
    super(new WorkSheetModel());
    this.model = (WorkSheetModel)getModel();
    this.onCellEdited = model.onCellUpdated;
    loadSerializedCells(cells);

    model.onCellUpdated.subscribe(this::onCellUpdated);
    initSelectionModel();
  }


  private void loadSerializedCells(SerializableCell[] cells) {
    for (final var cell : cells) {
      final var expr = cell.getExpression();
      final var pos = cell.getPosition();

      setValueAt(expr, pos.row, pos.col);
    }
  }

  private void onCellUpdated(Cell<?> c) {
    edited = true;
  }

  private void initSelectionModel() {
    final var model = getSelectionModel();
    getColumnModel()
      .getSelectionModel()
      .addListSelectionListener(this::handleCellSelection);

    model.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    model.addListSelectionListener(this::handleCellSelection);
    setCellSelectionEnabled(true);
  }

  private void handleCellSelection(ListSelectionEvent e) {
    final var rows = getSelectedRows();
    final var cols = getSelectedColumns();
    final var data = getSelectedCells(rows, cols);

    if (data.isEmpty()) {
      return;
    }

    onCellSelected.fire(
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


  @Override
  public void removeNotify() {
    super.removeNotify();
    model.onCellUpdated.unsubscribe(this::onCellUpdated);
  }
}
