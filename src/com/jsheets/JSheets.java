package com.jsheets;

import com.jsheets.components.dialogs.ErrorDialog;
import com.jsheets.events.StorageExceptionEventArgs;
import com.jsheets.frames.MainFrame;
import com.jsheets.services.ServiceRepository;
import com.jsheets.util.AutoSaveTimer;

public class JSheets {
  /**
   * Entry point for the application
   * 
   * Takes care of setting up the services and displaying the
   * main frame
   */
  public static void main(String[] args) {
    ServiceRepository.fontService.tryLoadFonts();
    ServiceRepository
      .storageService
      .onException
      .subscribe(JSheets::handleStorageException);

    setupShutdownHooks();

    final var mainFrame = new MainFrame();
    mainFrame.setVisible(true);

    setupAutosave();
    ServiceRepository
      .sessionService
      .tryLoadLastSession();
  }


  private static void setupShutdownHooks() {
    final var runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new Thread(JSheets::cleanup));
    runtime.addShutdownHook(
      new Thread(ServiceRepository.sessionService::saveCurrentSession)
    );
  }


  private static void cleanup() {
    ServiceRepository
      .storageService
      .onException
      .unsubscribe(JSheets::handleStorageException);
  }

  private static void handleStorageException(StorageExceptionEventArgs e) {
    ErrorDialog.show(e.exception);
  }


  private static void setupAutosave() {
    AutoSaveTimer
      .create(AutoSaveTimer.oneMinute)
      .start();
  }
}
