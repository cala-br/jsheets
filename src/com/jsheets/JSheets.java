package com.jsheets;

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
  }

  private static void cleanup() {
    ServiceRepository
      .storageService
      .onException
      .unsubscribe(ErrorDialog::show);
  }
}
