package com.jsheets.services.storage;

import com.jsheets.components.cells.SerializableCell;

public class WorksheetLoadedEvent {
  public final String title;
  public final SerializableCell[] cells;

  public WorksheetLoadedEvent(String title, SerializableCell[] cells) {
    this.title = title;
    this.cells = cells;
  }
}
