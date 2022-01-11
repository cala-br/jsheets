package com.jsheets.services.storage;

public class WorksheetSavedEvent {
  public final JSheetFile file;

  public WorksheetSavedEvent(JSheetFile file) {
    this.file = file;
  }
}
