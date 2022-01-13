package com.jsheets;

import javax.swing.Timer;

import com.jsheets.components.dialogs.ErrorDialog;
import com.jsheets.frames.MainFrame;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.StorageExceptionEvent;

public class JSheets {
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

  private static void handleStorageException(StorageExceptionEvent e) {
    ErrorDialog.show(e.exception);
  }


  private static void cleanup() {
    ServiceRepository
      .storageService
      .onException
      .unsubscribe(JSheets::handleStorageException);
  }

  private static void setupAutosave() {
    final var threeMinutes = 3 * 60 * 1000;
    final var saveTimer = new Timer(threeMinutes, e -> {
      ServiceRepository
        .sessionService
        .saveCurrentSession();
    });

    saveTimer.start();
  }
}
