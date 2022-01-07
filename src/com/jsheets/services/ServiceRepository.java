package com.jsheets.services;

import com.jsheets.services.font.FontService;
import com.jsheets.services.storage.FileStorageService;
import com.jsheets.services.storage.StorageService;

public class ServiceRepository {
  public final static FontService fontService = new FontService();
  public final static StorageService storageService = new FileStorageService();
}
