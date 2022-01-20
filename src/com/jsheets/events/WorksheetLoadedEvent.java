package com.jsheets.events;

import com.jsheets.cells.SerializableCell;
import com.jsheets.services.storage.JSheetFile;
import com.jsheets.services.storage.StorageService;

public class WorksheetLoadedEvent extends EventArgs<StorageService> {
  public final JSheetFile file;
  public final SerializableCell[] cells;

  public WorksheetLoadedEvent(StorageService sender, JSheetFile file, SerializableCell[] cells) {
    super(sender);
    this.file = file;
    this.cells = cells;
  }
}
