package com.jsheets.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpression {
  public static List<String> toPostfix(List<String> infix) {
    final var stack = new Stack<String>();
    final var result = new ArrayList<String>();

    for (final var c : infix) {
      if (Operators.getPrecedence(c) > 0) {
        while (!stack.isEmpty() && Operators.getPrecedence(stack.peek()) > Operators.getPrecedence(c)) {
          result.add(stack.pop());
        }
        stack.push(c);
      }
      else if (c.equals(")")) {
        var x = stack.pop();

        while (!x.equals("(")) {
          result.add(x);
          x = stack.pop();
        }
      }
      else if (c.equals("(")) {
        stack.push(c);
      }
      else {
        result.add(c);
      }
    }

    while (!stack.isEmpty()) {
      result.add(stack.pop());
    }

    return result;
  }
}
