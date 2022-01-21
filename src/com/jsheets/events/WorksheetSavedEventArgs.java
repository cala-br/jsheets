package com.jsheets.events;

import com.jsheets.services.storage.JSheetFile;
import com.jsheets.services.storage.StorageService;

/**
 * Fired when a file is saved by the {@link StorageService}.
 */
public class WorksheetSavedEventArgs extends EventArgs<StorageService> {
  /**
   * The file that was saved.
   */
  public final JSheetFile file;

  public WorksheetSavedEventArgs(StorageService sender, JSheetFile file) {
    super(sender);
    this.file = file;
  }
}
