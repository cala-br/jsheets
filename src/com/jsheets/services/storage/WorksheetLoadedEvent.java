package com.jsheets.services.storage;

import com.jsheets.cells.SerializableCell;
import com.jsheets.events.EventArgs;

public class WorksheetLoadedEvent extends EventArgs<StorageService> {
  public final JSheetFile file;
  public final SerializableCell[] cells;

  public WorksheetLoadedEvent(StorageService sender, JSheetFile file, SerializableCell[] cells) {
    super(sender);
    this.file = file;
    this.cells = cells;
  }
}
