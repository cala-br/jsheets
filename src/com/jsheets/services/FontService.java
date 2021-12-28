package com.jsheets.services;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontService {
  private static final FontService instance = new FontService();

  public static FontService getInstance() {
    return instance;
  }


  private Font segoeMdl2Assets;

  public Font getSegoeMdl2Assets() {
    return segoeMdl2Assets;
  }


  public boolean tryLoadFonts() {
    try {
      segoeMdl2Assets = loadTTF("assets/mdl2-icons.ttf", 12f);
      final var ge =
        GraphicsEnvironment.getLocalGraphicsEnvironment();
  
      ge.registerFont(segoeMdl2Assets);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  private Font loadTTF(String path, float size) throws IOException, FontFormatException {
    return Font
      .createFont(Font.TRUETYPE_FONT, new File(path))
      .deriveFont(size);
  }
}
