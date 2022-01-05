package com.jsheets.expressions;

import com.jsheets.components.cells.CellPosition;
import com.jsheets.components.cells.CellView;
import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.operands.BooleanConstant;
import com.jsheets.expressions.operands.CellExpression;
import com.jsheets.expressions.operands.NumericConstant;
import com.jsheets.util.BooleanUtil;
import com.jsheets.util.NumberUtil;

public class Operand {
  public static Expression<?, ?> parse(String token, CellView cells) {
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
