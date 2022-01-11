package com.jsheets.components.dialogs;

import java.io.File;

import javax.swing.JOptionPane;

import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;

public class JSheetFileSaver implements AutoCloseable {
  private final JSheetFileChooser chooser = new JSheetFileChooser();
  private final Worksheet worksheet;

  public JSheetFileSaver(Worksheet worksheet) {
    this.worksheet = worksheet;
    if (worksheet.hasBeenSaved()) {
      chooser.setSelectedFile(worksheet.getFile());
    }

    chooser.onFileChoosed.subscribe(this::saveChoosedFile);
  }


  public SaveDialogResult trySaveWithoutConfirmation() {
    if (!worksheet.hasBeenEdited()) {
      return SaveDialogResult.SAVED;
    }

    final var saveIfEdited = SaveIfEditedDialog.canSave();
    if (saveIfEdited != JOptionPane.OK_OPTION) {
      return SaveDialogResult.fromJOption(saveIfEdited);
    }

    if (!worksheet.hasBeenSaved()) {
      return saveWithConfirmation();
    }

    saveWorksheet();
    return SaveDialogResult.SAVED;
  }

  public SaveDialogResult saveWithConfirmation() {
    return SaveDialogResult.fromJChooserOption(
      chooser.showSaveDialog(null)
    );
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
