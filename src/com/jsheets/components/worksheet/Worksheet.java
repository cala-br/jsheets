package com.jsheets.components.worksheet;

import java.io.File;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import com.jsheets.cells.CellSpan;
import com.jsheets.cells.CellView;
import com.jsheets.cells.SerializableCell;
import com.jsheets.events.CellSelectionEvent;
import com.jsheets.events.CellUpdatedEvent;
import com.jsheets.events.Event;
import com.jsheets.model.WorkSheetModel;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;
import com.jsheets.services.storage.WorksheetSavedEvent;

public class Worksheet extends JTable {
  public final Event<CellSelectionEvent> onCellSelected = new Event<>();
  public final Event<CellUpdatedEvent> onCellEdited;
  private final WorkSheetModel model;

  private JSheetFile file = null;
  private boolean edited = false;
  public boolean hasBeenEdited() {
    return edited;
  }

  public boolean hasBeenSaved() {
    return file != null && !file.isTemporary();
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

    try {
      this.file = new JSheetFile(
        File.createTempFile("tmp-", ".new")
      );
    }
    catch (Exception e) {
      this.file = null;
    }

    this.model = (WorkSheetModel)getModel();
    this.onCellEdited = model.onCellUpdated;
    loadSerializedCells(cells);

    model.onCellUpdated.subscribe(this::onCellUpdated);
    initSelectionModel();

    ServiceRepository
      .storageService
      .onWorksheetSaved
      .subscribe(this::onSave);
  }


  private void loadSerializedCells(SerializableCell[] cells) {
    for (final var cell : cells) {
      final var expr = cell.getExpression();
      final var pos = cell.getPosition();

      setValueAt(expr, pos.row, pos.col);
    }
  }

  private void onCellUpdated(CellUpdatedEvent e) {
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
      new CellSelectionEvent(this, data, rows, cols)
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


  private void onSave(WorksheetSavedEvent e) {
    if (e.file.compareTo(file) == 0) {
      edited = false;
    }
  }


  @Override
  public void removeNotify() {
    super.removeNotify();
    model.onCellUpdated.unsubscribe(this::onCellUpdated);
    ServiceRepository
      .storageService
      .onWorksheetSaved
      .unsubscribe(this::onSave);
  }
}
