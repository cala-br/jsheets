package com.jsheets.services;

import com.jsheets.services.font.FontService;
import com.jsheets.services.session.SessionService;
import com.jsheets.services.storage.FileStorageService;
import com.jsheets.services.storage.StorageService;
import com.jsheets.services.worksheet_manager.WorksheetManagerService;

/**
 * Provides static access to singleton
 * instances of the services
 */
public class ServiceRepository {
  public final static FontService fontService = new FontService();
  public final static SessionService sessionService = new SessionService();
  public final static StorageService storageService = new FileStorageService();
  public final static WorksheetManagerService worksheetManager = new WorksheetManagerService();
}
