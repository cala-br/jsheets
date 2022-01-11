package com.jsheets.components.contextual_actions;

import javax.swing.JTextField;

import com.jsheets.cells.CellSelectionEvent;
import com.jsheets.components.misc.EnterKeyAdapter;
import com.jsheets.services.ServiceRepository;

public class ExpressionField extends JTextField {
  private final EnterKeyAdapter keyAdapter = new EnterKeyAdapter();
  private CellSelectionEvent currentEvent = null;

  public ExpressionField() {
    super();
    addKeyListener(keyAdapter);
    keyAdapter.onEnterPressed.subscribe(this::onEnterPressed);
  }

  public void setText(CellSelectionEvent e) {
    currentEvent = e;

    final var text = e.hasSingleCell
      ? e.data.get(0).getExpression()
      : e.data.onlyWithValue().toString();

    setText(text);
  }


  private void onEnterPressed() {
    if (currentEvent == null || !currentEvent.hasSingleCell) {
      return;
    }

    final var row = currentEvent.rows[0];
    final var col = currentEvent.columns[0];
    final var worksheet = ServiceRepository
      .worksheetManager
      .getCurrentlyActive();

    worksheet.setValueAt(getText(), row, col);
    worksheet.updateUI();
  }


  @Override
  public void removeNotify() {
    super.removeNotify();
    keyAdapter.onEnterPressed.unsubscribe(this::onEnterPressed);
  }
}
