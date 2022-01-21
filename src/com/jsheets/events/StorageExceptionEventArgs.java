package com.jsheets.events;

import com.jsheets.services.storage.StorageService;

/**
 * Fired when an exception occurs while trying to save
 * or load a file from the {@link StorageService}.
 */
public class StorageExceptionEventArgs extends EventArgs<StorageService> {
  /**
   * The exception that was thrown.
   */
  public final Exception exception;

  public StorageExceptionEventArgs(StorageService sender, Exception exception) {
    super(sender);
    this.exception = exception;
  }
}
