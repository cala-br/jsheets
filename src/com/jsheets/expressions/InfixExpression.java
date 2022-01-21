package com.jsheets.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents an expression that has the operators
 * between the operands.
 */
public class InfixExpression {
  private final ArrayList<String> result = new ArrayList<>();
  private final Stack<String> memo = new Stack<>();
  private final List<String> infix;


  /**
   * Converts an infix expression to a postfix expression.
   * A postfix expression is an expression that has the operator
   * after the operands.
   * @param infix The infix expression to convert. (E.g. {@code 1 + 2})
   * @return
   *  The resulting postfix expression, with operators and
   *  operands as elements of a list. (E.g. {@code ["1", "2", "+"]})
   */
  public static List<String> toPostfix(String infix) {
    final var tokens = ExpressionTokenizer.tokenize(infix);
    final var expr = new InfixExpression(tokens);
    return expr.toPostfix();
  }


  private InfixExpression(List<String> infix) {
    this.infix = infix;
  }

  private List<String> toPostfix() {
    for (final var term : infix) {
      Operator
        .fromSymbol(term)
        .ifPresentOrElse(
          (o) -> handleOperator(o),
          () -> result.add(term)
        );
    }
    popRemaining();
    return result;
  }

  private void handleOperator(Operator o) {
    if (o == Operator.CLOSING_PARENTHESIS) {
      popParentheses();
    }
    else if (o == Operator.OPENING_PARENTHESIS) {
      memo.push(o.getSymbol());
    }
    else {
      popPrecedingTerms(o);
    }
  }

  private void popParentheses() {
    var x = memo.pop();
    while (!x.equals("(")) {
      result.add(x);
      x = memo.pop();
    }
  }

  private void popPrecedingTerms(Operator o) {
    while (canPopPrecedingTerm(o)) {
      result.add(memo.pop());
    }

    memo.push(o.getSymbol());
  }

  private boolean canPopPrecedingTerm(Operator o) {
    return (
      !memo.isEmpty() && hasLowerPrecedenceThanPreceding(o)
    );
  }

  private boolean hasLowerPrecedenceThanPreceding(Operator o) {
    final var preceding = memo.peek();
    final var maybeOp = Operator.fromSymbol(preceding);
    return maybeOp.isPresent()
      ? maybeOp.get().comparePrecendeceTo(o) > 0
      : false;
  }

  private void popRemaining() {
    while (!memo.isEmpty()) {
      result.add(memo.pop());
    }
  }
}
