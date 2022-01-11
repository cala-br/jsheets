package com.jsheets.services.storage;

import com.jsheets.events.EventArgs;

public class WorksheetSavedEvent extends EventArgs<StorageService> {
  public final JSheetFile file;

  public WorksheetSavedEvent(StorageService sender, JSheetFile file) {
    super(sender);
    this.file = file;
  }
}
