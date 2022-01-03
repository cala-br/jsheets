package com.jsheets.components.expressions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.jsheets.components.cells.Cell;
import com.jsheets.components.cells.CellView;
import com.jsheets.exceptions.ParseException;

public class ExpressionTree<T, R> {
  public static final List<String> operators = Arrays.asList(
    "+", "-", "*", "/", "(", ")", "&&", "||", "<", "!"
  );

  public static Expression<?, ?> parse(String expression, CellView cells) throws ParseException {
    var split = splitExpression(expression);
    var i = split
      .stream()
      .filter(operators::contains)
      .findFirst();

    var in = split.indexOf(i.get());
    if (in == -1) {
      throw new ParseException();
    }

    try {
      var expr = toExpression(
        split.get(in),
        getOperandValue(split.get(in - 1), cells),
        getOperandValue(split.get(in + 1), cells)
      );

      return expr;
    }
    catch (Exception e) {
      throw new ParseException();
    }
  }

  private static List<String> splitExpression(String expression) {
    final var result = expression
      .replaceAll(" ", "")
      .split(getPattern());

    return Arrays.asList(result);
  }

  private static String getPattern() {
    final var WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    return String.join("|",
      operators
        .stream()
        .map(o -> Pattern.quote(o))
        .map(o -> String.format(WITH_DELIMITER, o))
        .toList()
    );
  }

  private static Expression<?, ?> toExpression(String operator, Object ...args) {
    return switch (operator) {
      case "+" -> new Add<>(
        NumericConstant.parse(args[0]),
        NumericConstant.parse(args[1])
      );
      case "-" -> new Subtract<>(
        NumericConstant.parse(args[0]),
        NumericConstant.parse(args[1])
      );
      case "*" -> new Multiply<>(
        NumericConstant.parse(args[0]),
        NumericConstant.parse(args[1])
      );
      case "/" -> new Divide<>(
        NumericConstant.parse(args[0]),
        NumericConstant.parse(args[1])
      );
      default -> throw new ParseException();
    };
  }

  private static Object getOperandValue(String operand, CellView cells) {
    try {
      var pos = Cell.parsePosition(operand);
      var cell = cells.get(pos[0], pos[1]);

      System.out.println(cell);
      return cell.getValue();
    }
    catch (Exception e) {
      return operand;
    }
  }
}
