package com.jsheets.components.dialogs;

import java.io.File;

import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;

public class JSheetFileSaver implements AutoCloseable {
  private final JSheetFileChooser chooser = new JSheetFileChooser();
  private final Worksheet worksheet;

  public JSheetFileSaver(Worksheet worksheet) {
    this.worksheet = worksheet;
    chooser.setSelectedFile(worksheet.getFile());
    chooser.onFileChoosed.subscribe(this::saveChoosedFile);
  }


  public boolean trySaveWithoutConfirmation() {
    final var canSave =
      worksheet.hasBeenEdited() &&
      SaveIfEditedDialog.canSave();

    if (!canSave) {
      return false;
    }

    if (worksheet.getFile() == null) {
      saveWithConfirmation();
    }
    else {
      saveWorksheet();
    }

    return true;
  }

  public void saveWithConfirmation() {
    chooser.showSaveDialog(null);
  }

  private void saveChoosedFile(File file) {
    final var jfile = new JSheetFile(file);
    if (!FileAlreadyExistsDialog.canSave(jfile)) {
      return;
    }

    worksheet.setFile(jfile);
    saveWorksheet();
  }

  private void saveWorksheet() {
    ServiceRepository
      .storageService
      .saveWorksheet(worksheet);
  }

  @Override
  public void close() {
    chooser.onFileChoosed.unsubscribe(this::saveChoosedFile);
  }
}
