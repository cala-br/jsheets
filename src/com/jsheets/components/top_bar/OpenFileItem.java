package com.jsheets.components.top_bar;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import com.jsheets.components.icons.OpenFileIcon;
import com.jsheets.services.ServiceRepository;

public class OpenFileItem extends JMenuItem {
  private final JFileChooser chooser = new JFileChooser();

  public OpenFileItem() {
    super();
    setText("Open");
    setIcon(new OpenFileIcon());

    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setCurrentDirectory(new File("." + File.separator));

    addActionListener(l -> {
      final var result = chooser.showOpenDialog(null);
      if (result != JFileChooser.APPROVE_OPTION) {
        return;
      }

      final var pickedFile = chooser
        .getSelectedFile()
        .getAbsolutePath();

      ServiceRepository
        .storageService
        .loadWorksheet(pickedFile);
    });
  }
}
