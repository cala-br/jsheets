package com.jsheets.components.contextual_actions;

import javax.swing.JTextField;

import com.jsheets.components.misc.EnterKeyAdapter;
import com.jsheets.events.CellSelectionEventArgs;
import com.jsheets.events.EventArgs;
import com.jsheets.services.ServiceRepository;

/**
 * Displays the expression relative to the currently selected
 * {@link Cell} or cells, and allows to edit it.
 */
public class ExpressionField extends JTextField {
  private final EnterKeyAdapter keyAdapter = new EnterKeyAdapter();
  private CellSelectionEventArgs currentEvent = null;

  public ExpressionField() {
    super();
    addKeyListener(keyAdapter);
    keyAdapter.onEnterPressed.subscribe(this::onEnterPressed);
  }

  /**
   * Extrapolates the expression from the given
   * event.
   * @param e The event containing the selected cells.
   */
  public void setText(CellSelectionEventArgs e) {
    currentEvent = e;

    final var text = e.hasSingleCell
      ? e.data.get(0).getExpression()
      : e.data.onlyWithValue().toString();

    setText(text);
  }


  private void onEnterPressed(EventArgs<EnterKeyAdapter> e) {
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
