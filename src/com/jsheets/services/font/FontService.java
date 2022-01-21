package com.jsheets.services.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontService {
  private Font segoeMdl2Assets;

  /**
   * @return
   *  The "segoe mdl2 assets" font from Microsoft,
   *  containing the icons used within windows 10
   */
  public Font getSegoeMdl2Assets() {
    return segoeMdl2Assets;
  }

  /**
   * Loads and registers the application fonts, rendering
   * them usable from the Swing components.
   * @return
   *  {@code true} if no error occurred, {@code false} otherwise
   */
  public synchronized boolean tryLoadFonts() {
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
