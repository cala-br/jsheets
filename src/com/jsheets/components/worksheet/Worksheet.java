package com.jsheets.components.worksheet;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import com.jsheets.cells.CellSpan;
import com.jsheets.cells.CellView;
import com.jsheets.cells.SerializableCell;
import com.jsheets.events.CellEditedEventArgs;
import com.jsheets.events.CellSelectionEventArgs;
import com.jsheets.events.CellUpdatedEventArgs;
import com.jsheets.events.Event;
import com.jsheets.events.WorksheetSavedEventArgs;
import com.jsheets.model.WorkSheetModel;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;

/**
 * A table that contains {@link Cell}s that can be used
 * for numeric computations.
 */
public class Worksheet extends JTable {
  public final Event<CellSelectionEventArgs> onCellSelected = new Event<>();
  public final Event<CellEditedEventArgs> onCellEdited = new Event<>();
  private final WorkSheetModel model;

  private JSheetFile file = null;
  private boolean edited = false;

  /**
   * Tells wether this table has been edited or not.
   * @return
   *  {@code true} if the table has been edited, {@code false} otherwise.
   */
  public boolean hasBeenEdited() {
    return edited;
  }

  /**
   * Tells wether the file linked to this cell is temporary
   * or null.
   * @return
   *  {@code true} if the table has been saved on a file created
   *  by the user, {@code false} otherwise.
   */
  public boolean hasBeenSaved() {
    return file != null && !file.isTemporary();
  }

  /**
   * @return
   *  The file where this sheet has been saved on.
   */
  public JSheetFile getFile() {
    return file;
  }

  /**
   * Sets the file where this table has or will be saved on.
   */
  public void setFile(JSheetFile file) {
    this.file = file;
  }

  /**
   * @return
   *  A view on the underlying {@link Cell} matrix.
   */
  public CellView getCellView() {
    return model.getView();
  }


  /**
   * Creates a new Worksheet with the given file, possibly loading
   * the provided cells.
   * @param file The file to link to the sheet.
   * @param cells A possible collection of cells to load.
   */
  public Worksheet(JSheetFile file, SerializableCell ...cells) {
    this(cells);
    setFile(file);
  }

  /**
   * Creates a new Worksheet, possibly loading the given cells.
   * @param cells
   *  A possible collection of cells to load.
   */
  public Worksheet(SerializableCell ...cells) {
    super(new WorkSheetModel());
    setFile(
      JSheetFile.maybeCreateTemporary()
    );

    this.model = (WorkSheetModel)getModel();
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

  private void onCellUpdated(CellUpdatedEventArgs e) {
    edited = true;
    onCellEdited.fire(
      new CellEditedEventArgs(this, e.cell)
    );
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
      new CellSelectionEventArgs(this, data, rows, cols)
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


  private void onSave(WorksheetSavedEventArgs e) {
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
