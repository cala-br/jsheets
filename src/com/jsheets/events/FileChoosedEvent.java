package com.jsheets.events;

import com.jsheets.components.dialogs.JSheetFileChooser;
import com.jsheets.services.storage.JSheetFile;

public class FileChoosedEvent extends EventArgs<JSheetFileChooser> {
  public final JSheetFile file;

  public FileChoosedEvent(JSheetFileChooser sender, JSheetFile file) {
    super(sender);
    this.file = file;
  }
}
