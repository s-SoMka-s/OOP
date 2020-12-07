package task4_1;


import task4_1.Implementations.Applier;
import task4_1.Interfaces.IApplier;
import task4_1.Interfaces.IDefiner;


import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MyCalculator{
    private Stack<Double> numbers;
    private IDefiner definer;
    private IApplier applier;

    /**
     * Конструктор класса MyCalculator
     * @param exprInf - выражение в инфиксной записи. Токены разделены пробелами
     * @param definerClassPath - полный путь до класса реализующего парсер токена
     * @param applierClassPass - полный путь до класса реализующего применение математических функций
     */
    public MyCalculator(String exprInf, String definerClassPath, String applierClassPass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        this.numbers = new Stack<>();

        this.definer = (IDefiner) Class.forName(definerClassPath).getDeclaredConstructor().newInstance();
        this.applier = (IApplier) Class.forName(applierClassPass).getDeclaredConstructor().newInstance();

        this.Calculate(exprInf);
    }

    private double Calculate(String exprInf) {
        var tokenizer = this.reverseTokenizer(new StringTokenizer(exprInf));

        while (tokenizer.hasMoreTokens()) {
            var token = tokenizer.nextToken().trim();

            var res = 0d;

            var operation = this.definer.defineBinaryOperation(token);
            if (operation != null){
                var arg1 = this.numbers.pop();
                var arg2 = this.numbers.pop();
                res = this.applier.applyOperation(operation, arg1, arg2);
            }
            else{
                var arg = this.numbers.pop();
                var function = this.definer.defineFunction(token);
                res = this.applier.applyFunction(function, arg);
            }

            this.numbers.push(res);
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

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }



    private boolean isLeftBracket(String token) {
        return token.equals("(");
    }

    private boolean isRightBracket(String token) {
        return token.equals(")");
    }
}
