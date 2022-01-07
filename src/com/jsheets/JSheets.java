package com.jsheets;

import com.jsheets.frames.MainFrame;
import com.jsheets.services.ServiceRepository;

public class JSheets {
  public static void main(String[] args) {
    ServiceRepository.fontService.tryLoadFonts();

    final var mainFrame = new MainFrame();
    mainFrame.setVisible(true);
  }
}
