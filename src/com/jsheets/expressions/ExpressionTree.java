package com.jsheets.expressions;

import java.util.List;
import java.util.Stack;

import com.jsheets.components.cells.CellView;
import com.jsheets.exceptions.ParseException;

public class ExpressionTree {
  private final Stack<Expression<?, ?>> memo = new Stack<>();
  private final List<String> postfix;
  private final CellView cells;

  private ExpressionTree(String expression, CellView cells) {
    this.cells = cells;
    this.postfix = InfixExpression.toPostfix(expression);
  }

  public static Expression<?, ?> parse(String expression, CellView cells) throws ParseException {
    return new ExpressionTree(expression, cells).parse();
  }


  private Expression<?, ?> parse() throws ParseException {
    for (final var t : postfix) {
      Operator
        .fromSymbol(t)
        .ifPresentOrElse(
          (o) -> pushOperation(o),
          () -> pushOperand(t)
        );
    }

    if (memo.empty()) {
      throw new ParseException();
    }

    return memo.pop();
  }

  private void pushOperand(String token) throws ParseException {
    memo.push(Operand.parse(token, cells));
  }

  private void pushOperation(Operator operator) throws ParseException {
    memo.push(Operation.parse(operator, memo));
  }
}
