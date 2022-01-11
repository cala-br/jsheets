package com.jsheets.components.icons;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JLabel;

import com.jsheets.services.ServiceRepository;

public abstract class FontIcon extends JLabel implements Icon {
  protected FontIcon(String icon) {
    super();
    setText(icon);
    setFont(getCustomFont());
  }

  private static Font getCustomFont() {
    return ServiceRepository
      .fontService
      .getSegoeMdl2Assets()
      .deriveFont(Font.BOLD);
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    final var g2d = (Graphics2D) g.create();
		final var map = (Map<?, ?>) Toolkit
      .getDefaultToolkit()
      .getDesktopProperty("awt.font.desktophints");

		if (map != null) {
		  g2d.addRenderingHints(map);
		}
		else {
			g2d.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON
      );
    }

		g2d.setFont(getFont());
		g2d.setColor(getForeground());
		final var fm = g2d.getFontMetrics();

		g2d.translate(x, y +	fm.getAscent());
		g2d.drawString(getText(), 0, 2);
		g2d.dispose();
  }

  @Override
  public int getIconWidth() {
    return getFont().getSize();
  }

  @Override
  public int getIconHeight() {
    return 16;
  }
}
