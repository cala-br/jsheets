package com.jsheets.services.storage;

import com.jsheets.cells.SerializableCell;
import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.events.Event;
import com.jsheets.events.StorageExceptionEventArgs;
import com.jsheets.events.WorksheetLoadedEventArgs;
import com.jsheets.events.WorksheetSavedEventArgs;

public abstract class StorageService {
  public final Event<WorksheetSavedEventArgs> onWorksheetSaved = new Event<>();
  public final Event<WorksheetLoadedEventArgs> onWorksheetLoaded = new Event<>();
  public final Event<StorageExceptionEventArgs> onException = new Event<>();

  /**
   * Serializes all the worksheet cells and dumps them
   * on the sheet's file.
   * @param worksheet
   *  The worskheet to save.
   */
  public void saveWorksheet(Worksheet worksheet) {
    saveWorksheet(
      worksheet.getFile(),
      worksheet
        .getCellView()
        .toSerializableArray()
    );
  }

  /**
   * Loads a worksheet from the provided path.
   * @param file
   *  The file containing the {@link SerializableCell}s.
   */
  public abstract void loadWorksheet(JSheetFile file);

  /**
   * Saves the cells into the specified file.
   * @param file The target file.
   * @param cells The cells to dump.
   */
  public abstract void saveWorksheet(JSheetFile file, SerializableCell[] cells);
}
