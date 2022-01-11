package com.jsheets.components.dialogs;

import javax.swing.JOptionPane;

import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.events.FileChoosedEvent;
import com.jsheets.services.ServiceRepository;

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

    return trySaveSilently();
  }


  public SaveDialogResult trySaveSilently() {
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

  private void saveChoosedFile(FileChoosedEvent e) {
    if (!FileAlreadyExistsDialog.canSave(e.file)) {
      return;
    }

    worksheet.setFile(e.file);
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
