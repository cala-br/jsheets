package com.jsheets.components.spreadsheet;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import com.jsheets.components.dialogs.JSheetFileSaver;
import com.jsheets.components.dialogs.SaveDialogResult;
import com.jsheets.components.worksheet.RowHeader;
import com.jsheets.components.worksheet.TableScrollPane;
import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.events.CellEditedEvent;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.WorksheetSavedEvent;
import com.jsheets.services.worksheet_manager.WorksheetManagerService;

public class Spreadsheet extends JTabbedPane {
  private final WorksheetManagerService worksheetManager = ServiceRepository.worksheetManager;
  private final Map<Worksheet, Component> componentsMap = new HashMap<>();

  public Spreadsheet() {
    super();
    addChangeListener(this::onTabChanged);

    ServiceRepository
      .storageService
      .onWorksheetSaved
      .subscribe(this::onWorksheetSaved);
  }


  private void onTabChanged(ChangeEvent e) {
    final var selectedIndex = getSelectedIndex();
    worksheetManager.setActive(selectedIndex);
  }


  private void onWorksheetSaved(WorksheetSavedEvent e) {
    final var saved = worksheetManager
      .getAll()
      .filter(w -> e.file.compareTo(w.getFile()) == 0)
      .findFirst();

    if (saved.isPresent()) {
      setTitleAt(
        worksheetManager.indexOf(saved.get()),
        e.file.getTitle()
      );
    }
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

    componentsMap.put(worksheet, wrappedSheet);
    worksheetManager.register(worksheet);
    worksheet.onCellEdited.subscribe(this::setEdited);

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


  @Override
  public void remove(int index) {
    final var worksheet =
      worksheetManager.getAt(index);

    final var saveResult = askToSaveBeforeRemoving(worksheet);
    if (saveResult == SaveDialogResult.CANCELED) {
      return;
    }

    super.remove(index);

    addNewWorksheetIfNoneRemain();
  }

  private void setEdited(CellEditedEvent e) {
    final var c = componentsMap.get(e.sender);
    final var i = indexOfComponent(c);
    final var t = getTitleAt(i);
    if (!t.endsWith("*")) {
      setTitleAt(i, t + "*");
    }
  }


  private SaveDialogResult askToSaveBeforeRemoving(Worksheet worksheet) {
    try (final var saver = new JSheetFileSaver(worksheet)) {
      return saver.trySaveWithoutConfirmation();
    }
  }

  private void addNewWorksheetIfNoneRemain() {
    if (getTabCount() == 0) {
      add(new Worksheet());
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
