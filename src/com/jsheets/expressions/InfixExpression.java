package com.jsheets.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpression {
  private final ArrayList<String> result = new ArrayList<>();
  private final Stack<String> memo = new Stack<>();
  private final List<String> infix;

  private InfixExpression(List<String> infix) {
    this.infix = infix;
  }


  public List<String> toPostfix() {
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

  public static List<String> toPostfix(String infix) {
    final var tokens = ExpressionTokenizer.tokenize(infix);
    final var expr = new InfixExpression(tokens);
    return expr.toPostfix();
  }
}
