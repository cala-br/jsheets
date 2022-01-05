package com.jsheets.expressions;

import java.util.Stack;

import com.jsheets.exceptions.ParseException;
import com.jsheets.expressions.operations.Add;
import com.jsheets.expressions.operations.And;
import com.jsheets.expressions.operations.Divide;
import com.jsheets.expressions.operations.Equal;
import com.jsheets.expressions.operations.GreaterEqual;
import com.jsheets.expressions.operations.GreaterThan;
import com.jsheets.expressions.operations.LessEqual;
import com.jsheets.expressions.operations.LessThan;
import com.jsheets.expressions.operations.Modulo;
import com.jsheets.expressions.operations.Multiply;
import com.jsheets.expressions.operations.Not;
import com.jsheets.expressions.operations.NotEqual;
import com.jsheets.expressions.operations.Or;
import com.jsheets.expressions.operations.Subtract;
import com.jsheets.expressions.types.BooleanExpression;
import com.jsheets.expressions.types.NumericExpression;

public class Operation {
  private final Stack<Expression<?, ?>> memo;
  private final String operator;

  private Operation(String operator, Stack<Expression<?, ?>> memo) {
    this.memo = memo;
    this.operator = operator;
  }

  public static Expression<?, ?> parse(String operator, Stack<Expression<?, ?>> memo) {
    return new Operation(operator, memo).parse();
  }


  private Expression<?, ?> parse() {
    if (Operators.isBinary(operator)) {
      return createBinaryExpression();
    }

    else if (Operators.isUnary(operator)) {
      return createUnaryExpression();
    }

    throw new ParseException();
  }

  private Expression<?, ?> createBinaryExpression() {
    final var right = memo.pop();
    final var left = memo.pop();

    if (left instanceof NumericExpression<?> p && right instanceof NumericExpression<?> n) {
      return numericOperation(operator, p, n);
    }
    else if (left instanceof BooleanExpression<?> p && right instanceof BooleanExpression<?> n) {
      return booleanOperation(operator, p, n);
    }

    throw new ParseException();
  }

  private Expression<?, ?> createUnaryExpression() {
    final var operand = memo.pop();

    if (operand instanceof BooleanExpression<?> o) {
      return booleanOperation(operator, o, null);
    }

    throw new ParseException();
  }


  private static <T1, T2> Expression<?, ?> numericOperation(
    String operator,
    NumericExpression<T1> a,
    NumericExpression<T2> b
  ) {
    return switch (operator) {
      case "+" -> new Add<>(a, b);
      case "-" -> new Subtract<>(a, b);
      case "*" -> new Multiply<>(a, b);
      case "/" -> new Divide<>(a, b);
      case "%" -> new Modulo<>(a, b);
      case "<" -> new LessThan<>(a, b);
      case ">" -> new GreaterThan<>(a, b);
      case "<=" -> new LessEqual<>(a, b);
      case ">=" -> new GreaterEqual<>(a, b);
      case "==" -> new Equal<>(a, b);
      case "!=" -> new NotEqual<>(a, b);
      default -> throw new ParseException();
    };
  }

  private static <T1, T2> Expression<?, ?> booleanOperation(
    String operator,
    BooleanExpression<T1> a,
    BooleanExpression<T2> b
  ) {
    return switch (operator) {
      case "&&" -> new And<>(a, b);
      case "||" -> new Or<>(a, b);
      case "!" -> new Not<>(a);
      default -> throw new ParseException();
    };
  }
}
