package com.jsheets.components.top_bar;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import com.jsheets.components.icons.SaveIcon;
import com.jsheets.services.ServiceRepository;

public class SaveItem extends JMenuItem {
  private final JFileChooser chooser = new JFileChooser();

  public SaveItem() {
    super();
    setText("Save");
    setIcon(new SaveIcon());

    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setCurrentDirectory(new File("." + File.separator));

    addActionListener(l -> {
      final var result = chooser.showSaveDialog(null);
      if (result != JFileChooser.APPROVE_OPTION) {
        return;
      }

      final var pickedFile = chooser
        .getSelectedFile()
        .getAbsolutePath();

      ServiceRepository.storageService.saveWorksheet(
        pickedFile,
        ServiceRepository
          .worksheetManager
          .getCurrentlyActive()
          .getCellView()
          .toSerializableArray()
      );
    });
  }
}
