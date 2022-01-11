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
  private final Operator operator;

  private Operation(Operator operator, Stack<Expression<?, ?>> memo) {
    this.memo = memo;
    this.operator = operator;
  }

  public static Expression<?, ?> parse(Operator operator, Stack<Expression<?, ?>> memo) {
    return new Operation(operator, memo).parse();
  }


  private Expression<?, ?> parse() {
    if (operator.is(Operator.Kind.BINARY)) {
      return createBinaryExpression();
    }

    else if (operator.is(Operator.Kind.UNARY)) {
      return createUnaryExpression();
    }

    throw new ParseException();
  }

  private Expression<?, ?> createBinaryExpression() {
    final var right = memo.pop();
    final var left = memo.pop();

    if (left instanceof NumericExpression<?> l && right instanceof NumericExpression<?> r) {
      return numericOperation(l, r);
    }
    else if (left instanceof BooleanExpression<?> l && right instanceof BooleanExpression<?> r) {
      return booleanOperation(l, r);
    }

    throw new ParseException();
  }

  private Expression<?, ?> createUnaryExpression() {
    final var operand = memo.pop();

    if (operand instanceof BooleanExpression<?> o) {
      return booleanOperation(o, null);
    }

    throw new ParseException();
  }


  private <T1, T2> Expression<?, ?> numericOperation(
    NumericExpression<T1> a,
    NumericExpression<T2> b
  ) {
    switch (operator) {
      case ADDITION: return new Add<>(a, b);
      case SUBTRACTION: return new Subtract<>(a, b);
      case MULTIPLICATION: return new Multiply<>(a, b);
      case DIVISION: return new Divide<>(a, b);
      case MODULO: return new Modulo<>(a, b);
      case LESS_THAN: return new LessThan<>(a, b);
      case GREATER_THAN: return new GreaterThan<>(a, b);
      case LESS_EQUAL: return new LessEqual<>(a, b);
      case GREATER_EQUAL: return new GreaterEqual<>(a, b);
      case EQUAL: return new Equal<>(a, b);
      case NOT_EQUAL: return new NotEqual<>(a, b);
      default: throw new ParseException();
    }
  }

  private <T1, T2> Expression<?, ?> booleanOperation(
    BooleanExpression<T1> a,
    BooleanExpression<T2> b
  ) {
    switch (operator) {
      case AND: return new And<>(a, b);
      case OR: return new Or<>(a, b);
      case NOT: return new Not<>(a);
      default: throw new ParseException();
    }
  }
}
