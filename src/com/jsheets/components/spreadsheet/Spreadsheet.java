package com.jsheets.components.spreadsheet;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import com.jsheets.components.dialogs.JSheetFileSaver;
import com.jsheets.components.worksheet.RowHeader;
import com.jsheets.components.worksheet.TableScrollPane;
import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.worksheet_manager.WorksheetManagerService;

public class Spreadsheet extends JTabbedPane {
  private final WorksheetManagerService worksheetManager =
    ServiceRepository.worksheetManager;

  public Spreadsheet() {
    super();
    addChangeListener(this::onTabChanged);
  }


  private void onTabChanged(ChangeEvent e) {
    final var selectedIndex = getSelectedIndex();
    worksheetManager.setActive(selectedIndex);
  }


  public void add(Worksheet worksheet) {
    final var file = worksheet.getFile();
    final var title = file == null
      ? "New"
      : file.getTitle();

    add(title, worksheet);
  }

  private void add(String title, Worksheet worksheet) {
    final var wrappedSheet =
      wrapWorksheet(worksheet);

    worksheetManager.register(worksheet);
    worksheet.onCellEdited.subscribe(c -> {
      setEdited(wrappedSheet);
    });

    addTab(title, wrappedSheet);
    refreshTabs();
  }

  private Container wrapWorksheet(Worksheet worksheet) {
    return RowHeader.wrap(
      new TableScrollPane(
        worksheet, 15
      )
    );
  }

  private void setEdited(Component component) {
    final var i = indexOfComponent(component);
    final var t = getTitleAt(i);
    if (!t.endsWith("*")) {
      setTitleAt(i, t + "*");
    }
  }

  @Override
  public void remove(int index) {
    final var worksheet =
      worksheetManager.getAt(index);

    maybeSave(worksheet);
    super.remove(index);
    worksheetManager.unregister(worksheet);
  }


  private void maybeSave(Worksheet worksheet) {
    try (final var saver = new JSheetFileSaver(worksheet)) {
      saver.trySaveWithoutConfirmation();
    }
  }

  private void refreshTabs() {
    for (int i = 0; i < getTabCount(); i++) {
      setCloseableTabAt(i);
    }
  }

  @Override
  public void setTitleAt(int index, String title) {
    super.setTitleAt(index, title);
    setCloseableTabAt(index);
  }

  private void setCloseableTabAt(int index) {
    setTabComponentAt(index, new CloseableTab(this, this::remove));
  }
}
