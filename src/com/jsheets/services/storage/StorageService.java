package com.jsheets.services.storage;

import com.jsheets.cells.SerializableCell;
import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.events.Event;

public abstract class StorageService {
  public final Event<WorksheetSavedEvent> onWorksheetSaved = new Event<>();
  public final Event<WorksheetLoadedEvent> onWorksheetLoaded = new Event<>();
  public final Event<StorageExceptionEvent> onException = new Event<>();

  public void saveWorksheet(Worksheet worksheet) {
    saveWorksheet(
      worksheet.getFile(),
      worksheet
        .getCellView()
        .toSerializableArray()
    );
  }

  public abstract void loadWorksheet(JSheetFile file);
  public abstract void saveWorksheet(JSheetFile file, SerializableCell[] cells);
}
