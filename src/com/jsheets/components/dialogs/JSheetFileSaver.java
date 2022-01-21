package com.jsheets.components.dialogs;

import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.events.FileChoosedEventArgs;
import com.jsheets.services.ServiceRepository;

/**
 * Saves a {@link Worksheet} into its linked
 * {@link JSheetFile} interactively.
 */
public class JSheetFileSaver implements AutoCloseable {
  private final JSheetFileChooser chooser = new JSheetFileChooser();
  private final Worksheet worksheet;

  /**
   * Creates a new {@code JSheetFileSaver}.
   * @param worksheet The worksheet that will be saved.
   */
  public JSheetFileSaver(Worksheet worksheet) {
    this.worksheet = worksheet;
    if (worksheet.hasBeenSaved()) {
      chooser.setSelectedFile(worksheet.getFile());
    }

    chooser.onFileChoosed.subscribe(this::saveChoosedFile);
  }


  /**
   * Tries to save the sheet silently if it hasn't been
   * edited.
   * @return
   *  The result of the operation.
   */
  public SaveDialogResult trySaveSilentlyIfNotEdited() {
    final var saveIfEdited = SaveIfEditedDialog.canSave(worksheet);
    if (saveIfEdited != SaveDialogResult.SAVED) {
      return saveIfEdited;
    }

    return trySaveSilently();
  }


  /**
   * Saves the worksheet directly if is not
   * stored on a temporary file, otherwise it
   * asks the user for where to save it.
   * @return
   *  The result of the operation.
   */
  public SaveDialogResult trySaveSilently() {
    if (!worksheet.hasBeenSaved()) {
      return saveWithConfirmation();
    }

    saveWorksheet();
    return SaveDialogResult.SAVED;
  }


  /**
   * Asks where to save the worksheet, and then saves it
   * if the user chooses to do so.
   * @return
   *  The result of the operation.
   */
  public SaveDialogResult saveWithConfirmation() {
    return SaveDialogResult.fromJChooserOption(
      chooser.showSaveDialog(null)
    );
  }

  private void saveChoosedFile(FileChoosedEventArgs e) {
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
