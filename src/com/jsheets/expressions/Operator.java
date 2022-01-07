package com.jsheets.expressions;

import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Stream;

public enum Operator {
  OPENING_PARENTHESIS("(", Precedence.NONE, Kind.GROUPING),
  CLOSING_PARENTHESIS(")", Precedence.NONE, Kind.GROUPING),

  OR ("||", Precedence.VERY_LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  AND("&&", Precedence.VERY_LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  LESS_THAN("<", Precedence.LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  GREATER_THAN(">", Precedence.LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  LESS_EQUAL("<=", Precedence.LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  GREATER_EQUAL(">=", Precedence.LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  EQUAL("==", Precedence.LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),
  NOT_EQUAL("!=", Precedence.LOW, EnumSet.of(Kind.LOGICAL, Kind.BINARY)),

  ADDITION("+", Precedence.NORMAL, EnumSet.of(Kind.ARITHMETIC, Kind.BINARY)),
  SUBTRACTION("-", Precedence.NORMAL, EnumSet.of(Kind.ARITHMETIC, Kind.BINARY)),
  MULTIPLICATION("*", Precedence.HIGH, EnumSet.of(Kind.ARITHMETIC, Kind.BINARY)),
  DIVISION("/", Precedence.HIGH, EnumSet.of(Kind.ARITHMETIC, Kind.BINARY)),
  MODULO("%", Precedence.HIGH, EnumSet.of(Kind.ARITHMETIC, Kind.BINARY)),

  NOT("!", Precedence.VERY_HIGH, EnumSet.of(Kind.LOGICAL, Kind.UNARY));

  public enum Precedence {
    NONE,
    VERY_LOW,
    LOW,
    NORMAL,
    HIGH,
    VERY_HIGH;
  }

  public enum Kind {
    GROUPING,
    LOGICAL,
    ARITHMETIC,
    BINARY,
    UNARY;
  }

  private final String symbol;
  private final Precedence precedence;
  private final EnumSet<Kind> kind;


  private Operator(String symbol, Precedence precedence, Kind kind) {
    this(symbol, precedence, EnumSet.of(kind));
  }

  private Operator(String symbol, Precedence precedence, EnumSet<Kind> kind) {
    this.symbol = symbol;
    this.precedence = precedence;
    this.kind = kind;
  }


  public String getSymbol() {
    return symbol;
  }

  public int getPrecedence() {
    return precedence.ordinal();
  }

  public int comparePrecendeceTo(Operator other) {
    return getPrecedence() - other.getPrecedence();
  }

  public boolean is(Kind kind) {
    return this.kind.contains(kind);
  }


  public static boolean isOperator(String symbol) {
    return EnumSet
      .allOf(Operator.class)
      .stream()
      .anyMatch(o -> o.symbol.equals(symbol));
  }

  public static Optional<Operator> fromSymbol(String symbol) {
    return EnumSet
      .allOf(Operator.class)
      .stream()
      .filter(o -> o.symbol.equals(symbol))
      .findFirst();
  }

  public static Stream<Operator> getAllByLargestPossibleSymbol() {
    return EnumSet
      .allOf(Operator.class)
      .stream()
      .sorted((a, b) -> b.symbol.length() - a.symbol.length());
  }
}
