package com.jsheets;

import com.jsheets.frames.MainFrame;
import com.jsheets.services.FontService;

public class JSheets {
  public static void main(String[] args) {
    FontService
      .getInstance()
      .tryLoadFonts();

    final var mainFrame = new MainFrame();
    mainFrame.setVisible(true);
  }
}
