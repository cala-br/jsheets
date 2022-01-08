package com.jsheets.services.storage;

import com.jsheets.components.cells.SerializableCell;

public class WorksheetLoadedEvent {
  public final JSheetPath path;
  public final SerializableCell[] cells;

  public WorksheetLoadedEvent(JSheetPath path, SerializableCell[] cells) {
    this.path = path;
    this.cells = cells;
  }
}
