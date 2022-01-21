package com.jsheets.events;

import com.jsheets.components.dialogs.JSheetFileChooser;
import com.jsheets.services.storage.JSheetFile;

/**
 * Fired when a file is choosed from the {@link JSheetFileChooser}.
 */
public class FileChoosedEventArgs extends EventArgs<JSheetFileChooser> {
  /**
   * The file that was choosed.
   */
  public final JSheetFile file;

  public FileChoosedEventArgs(JSheetFileChooser sender, JSheetFile file) {
    super(sender);
    this.file = file;
  }
}
