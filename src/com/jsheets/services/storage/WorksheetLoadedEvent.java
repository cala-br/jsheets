package com.jsheets.services.storage;

import com.jsheets.components.cells.SerializableCell;

public class WorksheetLoadedEvent {
  public final SerializableCell[] cells;

  public WorksheetLoadedEvent(SerializableCell[] cells) {
    this.cells = cells;
  }
}
