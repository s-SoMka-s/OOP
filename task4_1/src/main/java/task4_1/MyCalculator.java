package task4_1;

import java.util.*;

public class MyCalculator {
    private final String[] operations = {"+", "-", "/", "*"};
    private final String[] functions = {"sin", "cos", "log", "pow", "sqrt"};

    private Stack<Double> numbers;


    public MyCalculator() {
        this.numbers = new Stack<>();
        this.Calculate("sin + - 1 2 1");
    }

    private double Calculate(String exprInf) {
        var tokenizer = this.reverseTokenizer(new StringTokenizer(exprInf));

        while (tokenizer.hasMoreTokens()) {
            var token = tokenizer.nextToken().trim();
            if (isFunction(token)) {
                applyFunction(token);
            }
            else if (isOperation(token)) {
               applyOperation(token);
            }
            else if (isNumber(token)) {
                this.numbers.push(Double.parseDouble(token));
            }
        }

        var res = this.numbers.pop();
        System.out.println(res);
        return 0d;
    }

    private StringTokenizer reverseTokenizer(StringTokenizer tokenizer) {
        var reversedString = "";
        while (tokenizer.hasMoreTokens()) {
            reversedString = tokenizer.nextToken() + " " + reversedString;
        }

        return new StringTokenizer(reversedString);
    }

    private void applyOperation(String token){
        var op1 = this.numbers.pop();
        var op2 = this.numbers.pop();

        var res = 0d;

        switch (token){
            case "+":
                res = op1 + op2;
                break;
            case "-":
                res = op1 - op2;
                break;
            case "*":
                res = op1 * op2;
                break;
            case "/":
                res = op1 / op2;
                break;
            default:
                break;
        }

        this.numbers.push(res);
    }

    private void applyFunction(String token) {
        var res = this.numbers.pop();
        switch (token) {
            case "sin":
                res = Math.sin(res);
                break;
            case "cos":
                res = Math.cos(res);
                break;
            case "log":
                res = Math.log(res);
                break;
            case "sqrt":
                res = Math.sqrt(res);
                break;
            default:
                break;
        }

        this.numbers.push(res);
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private boolean isOperation(String token) {
        for (var operation : this.operations) {
            if (operation.equals(token)) {
                return true;
            }
        }

        return false;
    }

    private boolean isFunction(String token) {
        for (var function : this.functions) {
            if (function.equals(token)) {
                return true;
            }
        }

        return false;
    }

    private boolean isLeftBracket(String token) {
        return token.equals("(");
    }

    private boolean isRightBracket(String token) {
        return token.equals(")");
    }
}
