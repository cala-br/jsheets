package com.jsheets.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.jsheets.components.contextual_actions.ContextualActions;
import com.jsheets.components.spreadsheet.Spreadsheet;
import com.jsheets.components.top_bar.TopBar;
import com.jsheets.components.worksheet.Worksheet;
import com.jsheets.events.CellSelectionEventArgs;
import com.jsheets.events.WorksheetLoadedEventArgs;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.JSheetFile;

public class MainFrame extends JFrame {
  private final ContextualActions actions = new ContextualActions();
  private final Spreadsheet spreadsheet = new Spreadsheet();

  private boolean alreadyPacked = false;

  public MainFrame() {
    super();

    setSize(480, 320);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());
    setJMenuBar(new TopBar());

    add(actions, BorderLayout.NORTH);
    add(spreadsheet, BorderLayout.CENTER);

    maybeAddEmptyWorksheet();

    ServiceRepository
      .storageService
      .onWorksheetLoaded
      .subscribe(this::onWorksheetLoaded);
  }


  private void maybeAddEmptyWorksheet() {
    final var anyExisting = ServiceRepository
      .sessionService
      .anyInLastSession();

    if (!anyExisting) {
      spreadsheet.add(
        addWorksheetHandlers(new Worksheet())
      );

      maybePack();
    }
  }


  private void onWorksheetLoaded(WorksheetLoadedEventArgs e) {
    if (isAlreadyOpen(e.file)) {
      return;
    }

    final var worksheet = addWorksheetHandlers(
      new Worksheet(e.file, e.cells)
    );

    spreadsheet.add(worksheet);
    maybePack();
  }

  private boolean isAlreadyOpen(JSheetFile file) {
    return ServiceRepository
      .worksheetManager
      .getAll()
      .filter(w -> w.getFile() != null)
      .anyMatch(w -> file.compareTo(w.getFile()) == 0);
  }

  private Worksheet addWorksheetHandlers(Worksheet worksheet) {
    worksheet
      .onCellSelected
      .subscribe(this::onCellSelected);

    return worksheet;
  }

  private void onCellSelected(CellSelectionEventArgs e) {
    actions.setSelectedCell(e.rows, e.columns);
    actions.isExpressionEditable(e.hasSingleCell);
    actions.setExpression(e);
  }


  private void maybePack() {
    if (!alreadyPacked) {
      pack();
      alreadyPacked = true;
    }
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    ServiceRepository
      .worksheetManager
      .getAll()
      .forEach(w -> w
        .onCellSelected
        .unsubscribe(this::onCellSelected)
      );

    ServiceRepository
      .storageService
      .onWorksheetLoaded
      .unsubscribe(this::onWorksheetLoaded);
  }
}
