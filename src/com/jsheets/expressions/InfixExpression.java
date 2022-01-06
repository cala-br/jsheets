package com.jsheets.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpression {
  public static List<String> toPostfix(String infix) {
    return toPostfix(
      ExpressionTokenizer.tokenize(infix)
    );
  }

  public static List<String> toPostfix(List<String> infix) {
    final var memo = new Stack<String>();
    final var result = new ArrayList<String>();

    for (final var c : infix) {
      if (Operator.isOperator(c)) {
        while (!memo.isEmpty() && comparePrecedence(memo.peek(), c) > 0) {
          result.add(memo.pop());
        }
        memo.push(c);
      }
      else if (c.equals(")")) {
        var x = memo.pop();

        while (!x.equals("(")) {
          result.add(x);
          x = memo.pop();
        }
      }
      else if (c.equals("(")) {
        memo.push(c);
      }
      else {
        result.add(c);
      }
    }

    while (!memo.isEmpty()) {
      result.add(memo.pop());
    }

    return result;
  }

  public static int comparePrecedence(String a, String b) {
    final var first = Operator.fromSymbol(a);
    final var second = Operator.fromSymbol(b);

    return first.isPresent() && second.isPresent()
      ? first.get().comparePrecendeceTo(second.get())
      : -1;
  }
}
