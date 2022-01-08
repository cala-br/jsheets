package com.jsheets.components.top_bar;

import java.io.File;

import javax.swing.JMenuItem;

import com.jsheets.components.dialogs.JSheetFileChooser;
import com.jsheets.components.icons.OpenFileIcon;
import com.jsheets.services.ServiceRepository;

public class OpenFileItem extends JMenuItem {
  private final JSheetFileChooser chooser = new JSheetFileChooser();

  public OpenFileItem() {
    super();
    setText("Open");
    setIcon(new OpenFileIcon());
    addActionListener(l -> chooser.showOpenDialog(null));

    chooser
      .onFileChoosed
      .subscribe(this::loadWorksheet);
  }

  private void loadWorksheet(File f) {
    ServiceRepository
      .storageService
      .loadWorksheet(f.getAbsolutePath());
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    chooser.onFileChoosed.unsubscribe(this::loadWorksheet);
  }
}
