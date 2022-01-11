package com.jsheets;

import javax.swing.Timer;

import com.jsheets.components.dialogs.ErrorDialog;
import com.jsheets.frames.MainFrame;
import com.jsheets.services.ServiceRepository;

public class JSheets {
  public static void main(String[] args) {
    ServiceRepository.fontService.tryLoadFonts();
    ServiceRepository
      .storageService
      .onException
      .subscribe(ErrorDialog::show);

    Runtime
      .getRuntime()
      .addShutdownHook(new Thread(JSheets::cleanup));

    final var mainFrame = new MainFrame();
    mainFrame.setVisible(true);

    setupAutosave();
  }

  private static void cleanup() {
    ServiceRepository
      .storageService
      .onException
      .unsubscribe(ErrorDialog::show);
  }

  private static void setupAutosave() {
    final var threeMinutes = 3 * 60 * 1000;
    final var saveTimer = new Timer(threeMinutes, e -> {
      ServiceRepository
        .worksheetManager
        .getAll()
        .forEach(ServiceRepository.storageService::saveWorksheet);
    });

    saveTimer.start();
  }
}
