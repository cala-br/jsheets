package com.jsheets.services.storage;

import com.jsheets.components.cells.SerializableCell;
import com.jsheets.util.Event;

public abstract class StorageService {
  public final Event<WorksheetSavedEvent> onWorksheetSaved = new Event<>();
  public final Event<WorksheetLoadedEvent> onWorksheetLoaded = new Event<>();
  public final Event<Exception> onException = new Event<>();

  public abstract void loadWorksheet(JSheetPath path);
  public abstract void saveWorksheet(JSheetPath path, SerializableCell[] cells);
}
