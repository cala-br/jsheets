package com.jsheets.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.jsheets.components.contextual_actions.ContextualActions;
import com.jsheets.components.spreadsheet.Spreadsheet;
import com.jsheets.components.top_bar.TopBar;
import com.jsheets.components.work_sheet.CellSelectionEvent;
import com.jsheets.components.work_sheet.WorkSheet;
import com.jsheets.services.ServiceRepository;
import com.jsheets.services.storage.WorksheetLoadedEvent;

public class MainFrame extends JFrame {
  private final ContextualActions actions = new ContextualActions();
  private final Spreadsheet spreadsheet = new Spreadsheet();

  public MainFrame() {
    super();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setJMenuBar(new TopBar());

    add(actions, BorderLayout.NORTH);
    add(spreadsheet, BorderLayout.CENTER);

    spreadsheet.add(
      createWorksheet()
    );

    ServiceRepository
      .storageService
      .onWorksheetLoaded
      .subscribe(this::onWorksheetLoaded);

    pack();
  }


  private void onWorksheetLoaded(WorksheetLoadedEvent e) {
    final var worksheet = createWorksheet();
    worksheet.loadSerializedCells(e.cells);
    spreadsheet.add(e.title, worksheet);
  }

  private WorkSheet createWorksheet() {
    return new WorkSheet(this::onCellSelected);
  }

  private void onCellSelected(CellSelectionEvent e) {
    actions.setSelectedCell(e.rows, e.columns);
    actions.isExpressionEditable(e.hasSingleCell);
    actions.setExpression(e);
  }
}
