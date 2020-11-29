package task4_1;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class MyCalculator {
    private final String[] operations = {"+", "-", "/", "*"};
    private final String[] functions = {"sin", "cos", "log", "pow", "sqrt"};



    public MyCalculator() {
        this.Calculate("+ 4 5.0");
    }

    private double Calculate(String exprInf) {
        var tokenizer = new StringTokenizer(exprInf);
        var stack = new Stack<>();

        while (tokenizer.hasMoreTokens()) {
            var lexeme = tokenizer.nextToken().trim();

        }

        return 0d;
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
        for (var operation : this.operations){
            if (operation.equals(token)){
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
