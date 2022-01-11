package com.jsheets.services.storage;

import com.jsheets.events.EventArgs;

public class StorageExceptionEvent extends EventArgs<StorageService> {
  public final Exception exception;

  public StorageExceptionEvent(StorageService sender, Exception exception) {
    super(sender);
    this.exception = exception;
  }
}
