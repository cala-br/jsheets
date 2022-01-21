package com.jsheets.expressions;

import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents an operator.
 */
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


  /**
   * @return
   *  The symbol of this operator. (E.g. {@code "+"})
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * @return
   *  The ordinal {@link Precedence} of this operator relative to the others.
   */
  public int getPrecedence() {
    return precedence.ordinal();
  }

  /**
   * Compares this operator's precedence to another one.
   * @param other The other operator.
   * @return
   *  A value lower than 0 if the precedence is lower,
   *  a value higher than 0 if the precedence is higher,
   *  a value equal to 0 if they are the same.
   */
  public int comparePrecendeceTo(Operator other) {
    return getPrecedence() - other.getPrecedence();
  }

  /**
   * Checks if this operator is of the given kind.
   * @param kind The possible kind of this operator.
   * @return {@code true} if it is of the given kind, {@code false} otherwise.
   */
  public boolean is(Kind kind) {
    return this.kind.contains(kind);
  }


  /**
   * Tells wether the given symbol matches an operator.
   * @param symbol The symbol to check.
   * @return {@code true} if the symbol belongs to an operator, {@code false} otherwise.
   */
  public static boolean isOperator(String symbol) {
    return EnumSet
      .allOf(Operator.class)
      .stream()
      .anyMatch(o -> o.symbol.equals(symbol));
  }

  /**
   * Creates a new operator from a given symbol.
   * @param symbol A symbol representing an operator.
   * @return The corresponding operator, or an empty optional.
   */
  public static Optional<Operator> fromSymbol(String symbol) {
    return EnumSet
      .allOf(Operator.class)
      .stream()
      .filter(o -> o.symbol.equals(symbol))
      .findFirst();
  }

  /**
   * Streams all the operators from the longest to
   * shortest, by symbol.
   */
  public static Stream<Operator> getAllByLargestPossibleSymbol() {
    return EnumSet
      .allOf(Operator.class)
      .stream()
      .sorted((a, b) -> b.symbol.length() - a.symbol.length());
  }
}
