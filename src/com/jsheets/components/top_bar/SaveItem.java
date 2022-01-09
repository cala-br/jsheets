package com.jsheets.components.top_bar;


import java.io.File;

import javax.swing.JMenuItem;

import com.jsheets.components.cells.SerializableCell;
import com.jsheets.components.dialogs.FileAlreadyExistsDialog;
import com.jsheets.components.dialogs.JSheetFileChooser;
import com.jsheets.components.icons.SaveIcon;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;

public class SaveItem extends JMenuItem {
  private final JSheetFileChooser chooser = new JSheetFileChooser();

  public SaveItem() {
    super();
    setText("Save");
    setIcon(new SaveIcon());
    addActionListener(l -> chooser.showSaveDialog(null));

    chooser
      .onFileChoosed
      .subscribe(this::saveWorksheet);
  }

  private void saveWorksheet(File f) {
    if (!FileAlreadyExistsDialog.canFileBeSaved(new JSheetFile(f))) {
      return;
    }

    ServiceRepository.storageService.saveWorksheet(
      new JSheetFile(f),
      getSerializableCells()
    );
  }

  private SerializableCell[] getSerializableCells() {
    return ServiceRepository
      .worksheetManager
      .getCurrentlyActive()
      .getCellView()
      .toSerializableArray();
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    chooser.onFileChoosed.unsubscribe(this::saveWorksheet);
  }
}
