package com.jsheets.services.storage;

import com.jsheets.cells.SerializableCell;

public class WorksheetLoadedEvent {
  public final JSheetFile file;
  public final SerializableCell[] cells;

  public WorksheetLoadedEvent(JSheetFile file, SerializableCell[] cells) {
    this.file = file;
    this.cells = cells;
  }
}
