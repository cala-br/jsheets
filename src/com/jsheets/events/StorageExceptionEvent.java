package com.jsheets.events;

import com.jsheets.services.storage.StorageService;

public class StorageExceptionEvent extends EventArgs<StorageService> {
  public final Exception exception;

  public StorageExceptionEvent(StorageService sender, Exception exception) {
    super(sender);
    this.exception = exception;
  }
}
