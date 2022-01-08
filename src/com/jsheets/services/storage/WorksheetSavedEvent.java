package com.jsheets.services.storage;

public class WorksheetSavedEvent {
  public final JSheetPath path;

  public WorksheetSavedEvent(JSheetPath path) {
    this.path = path;
  }
}
