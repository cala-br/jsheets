package com.jsheets.components.contextual_actions;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.jsheets.components.icons.FunctionIcon;
import com.jsheets.events.CellSelectionEventArgs;

/**
 * An app bar containing elements that change based
 * on the selected {@link Worskheet} cells.
 */
public class ContextualActions extends JPanel {
  private final SelectedCellsField selectedCell = new SelectedCellsField();
  private final ExpressionField expression = new ExpressionField();

  public ContextualActions() {
    super();

    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    add(selectedCell);
    add(Box.createRigidArea(new Dimension(8, 0)));
    add(new FunctionIcon());
    add(Box.createRigidArea(new Dimension(2, 0)));
    add(expression);
  }

  /**
   * Sets the span of selected cells.
   * @param rows The selected rows.
   * @param cols The selected columns.
   */
  public void setSelectedCell(int[] rows, int[] cols) {
    selectedCell.updateText(rows, cols);
  }

  /**
   * Sets the expression to render inside the expression
   * field.
   * It changes based on how many cells have been selected.
   * 
   * @param e
   *  The event to extrapolate the expression from.
   */
  public void setExpression(CellSelectionEventArgs e) {
    expression.setText(e);
  }

  /**
   * Tells wether the expression field should be editable
   * or not.
   * @param editable
   *  {@code true} if the expression should be edited,
   *  {@code false} otherwise.
   */
  public void isExpressionEditable(boolean editable) {
    expression.setEditable(editable);
  }
}
