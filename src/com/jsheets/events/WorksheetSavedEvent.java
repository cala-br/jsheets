package com.jsheets.events;

import com.jsheets.services.storage.JSheetFile;
import com.jsheets.services.storage.StorageService;

public class WorksheetSavedEvent extends EventArgs<StorageService> {
  public final JSheetFile file;

  public WorksheetSavedEvent(StorageService sender, JSheetFile file) {
    super(sender);
    this.file = file;
  }
}
