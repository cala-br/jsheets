package com.jsheets.events;

import com.jsheets.cells.SerializableCell;
import com.jsheets.services.storage.JSheetFile;
import com.jsheets.services.storage.StorageService;

/**
 * Fired when a file is loaded from the {@link StorageService}.
 */
public class WorksheetLoadedEventArgs extends EventArgs<StorageService> {
  /**
   * The loaded file.
   */
  public final JSheetFile file;

  /**
   * The deserialized cells.
   */
  public final SerializableCell[] cells;

  public WorksheetLoadedEventArgs(StorageService sender, JSheetFile file, SerializableCell[] cells) {
    super(sender);
    this.file = file;
    this.cells = cells;
  }
}
