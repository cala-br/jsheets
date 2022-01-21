package com.jsheets.expressions;

import com.jsheets.cells.CellPosition;
import com.jsheets.cells.CellView;
import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.operands.BooleanConstant;
import com.jsheets.expressions.operands.CellExpression;
import com.jsheets.expressions.operands.NumericConstant;
import com.jsheets.util.BooleanUtil;
import com.jsheets.util.NumberUtil;

/**
 * Represents an operand.
 */
public class Operand {
  /**
   * Converts an operand to an expression.
   * @param token The possible operand.
   * @param cells A view of the cell matrix. (For cell operands)
   * @return A new expression representing this operand.
   * @throws ParseException
   *  If the given token is not an operand.
   */
  public static Expression<?, ?> parse(String token, CellView cells) throws ParseException {
    if (NumberUtil.isNumber(token)) {
      return NumericConstant.parse(token);
    }

    else if (BooleanUtil.isBoolean(token)) {
      return BooleanConstant.parse(token);
    }

    else if (CellPosition.isValidPosition(token)) {
      return new CellExpression(
        cells.get(
          CellPosition.from(token)
        )
      );
    }

    throw new ParseException();
  }
}
