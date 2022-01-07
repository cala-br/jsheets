package com.jsheets.services.storage;

import java.net.URI;

import com.jsheets.components.cells.SerializableCell;
import com.jsheets.util.Event;

public abstract class StorageService {
  public final Event<String> onWorksheetSaved = new Event<>();
  public final Event<WorksheetLoadedEvent> onWorksheetLoaded = new Event<>();

  public abstract void loadWorksheet(URI location);
  public abstract void saveWorksheet(URI location, SerializableCell[] cells);
}
