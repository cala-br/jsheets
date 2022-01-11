package com.jsheets.components.top_bar;


import javax.swing.JMenuItem;

import com.jsheets.components.dialogs.JSheetFileSaver;
import com.jsheets.components.icons.SaveIcon;
import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.services.ServiceRepository;

public class SaveItem extends JMenuItem {
  public SaveItem() {
    super();
    setText("Save");
    setIcon(new SaveIcon());
    addActionListener(l -> save());
  }

  private void save() {
    final var worksheet = getActiveWorksheet();
    final var saver = new JSheetFileSaver(worksheet);

    try (saver) {
      saver.trySaveSilently();
    }
  }

  private Worksheet getActiveWorksheet() {
    return ServiceRepository
      .worksheetManager
      .getCurrentlyActive();
  }
}
