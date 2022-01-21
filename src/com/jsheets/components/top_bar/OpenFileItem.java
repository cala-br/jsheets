package com.jsheets.components.top_bar;

import javax.swing.JMenuItem;

import com.jsheets.components.dialogs.JSheetFileChooser;
import com.jsheets.components.icons.OpenFileIcon;
import com.jsheets.events.FileChoosedEventArgs;
import com.jsheets.services.ServiceRepository;

/**
 * Prompts to open an existing worksheet when
 * pressed.
 */
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

  private void loadWorksheet(FileChoosedEventArgs e) {
    ServiceRepository
      .storageService
      .loadWorksheet(e.file);
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    chooser.onFileChoosed.unsubscribe(this::loadWorksheet);
  }
}
