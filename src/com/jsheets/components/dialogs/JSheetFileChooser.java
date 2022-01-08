package com.jsheets.components.dialogs;

import java.awt.Component;
import java.io.File;
import java.util.function.Function;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jsheets.util.Event;

public class JSheetFileChooser extends JFileChooser {
  public final Event<File> onFileChoosed = new Event<>();

  public JSheetFileChooser() {
    super();
    setAcceptAllFileFilterUsed(false);
    setFileFilter(new FileNameExtensionFilter("JSheets", "jsheet"));
    setFileSelectionMode(FILES_ONLY);
    setCurrentDirectory(new File("."));
  }


  @Override
  public int showSaveDialog(Component parent) {
    return showDialog(this::showSaveDialog, parent);
  }

  @Override
  public int showOpenDialog(Component parent) {
    return showDialog(this::showOpenDialog, parent);
  }


  private int showDialog(
    Function<Component, Integer> action,
    Component parent
  ) {
    final var result = action.apply(parent);
    maybeInvokeEvent(result);
    return result;
  }

  private void maybeInvokeEvent(int result) {
    if (result == JFileChooser.APPROVE_OPTION) {
      onFileChoosed.fire(
        getSelectedFile()
      );
    }
  }
}
