package com.jsheets.components.dialogs;

import java.awt.Component;
import java.io.File;
import java.util.function.Supplier;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jsheets.events.Event;
import com.jsheets.events.FileChoosedEvent;
import com.jsheets.services.storage.JSheetFile;

public class JSheetFileChooser extends JFileChooser {
  public final Event<FileChoosedEvent> onFileChoosed = new Event<>();

  public JSheetFileChooser() {
    super();
    setAcceptAllFileFilterUsed(false);
    setFileFilter(new FileNameExtensionFilter("JSheets", "jsheet"));
    setFileSelectionMode(FILES_ONLY);
    setCurrentDirectory(new File("."));
  }


  @Override
  public int showSaveDialog(Component parent) {
    return showDialogAndFireEvent(() -> super.showSaveDialog(parent));
  }

  @Override
  public int showOpenDialog(Component parent) {
    return showDialogAndFireEvent(() -> super.showOpenDialog(parent));
  }


  private int showDialogAndFireEvent(Supplier<Integer> action) {
    final var result = action.get();
    maybeFireEvent(result);
    return result;
  }

  private void maybeFireEvent(int result) {
    if (result == JFileChooser.APPROVE_OPTION) {
      onFileChoosed.fire(
        new FileChoosedEvent(this, new JSheetFile(getSelectedFile()))
      );
    }
  }
}
