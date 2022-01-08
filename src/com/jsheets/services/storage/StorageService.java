package com.jsheets.services.storage;

import com.jsheets.components.cells.SerializableCell;
import com.jsheets.util.Event;

public abstract class StorageService {
  public final Event<String> onWorksheetSaved = new Event<>();
  public final Event<WorksheetLoadedEvent> onWorksheetLoaded = new Event<>();
  public final Event<Exception> onException = new Event<>();

  public abstract void loadWorksheet(String path);
  public abstract void saveWorksheet(String path, SerializableCell[] cells);
}
