package task4_1.Implementations;

import task4_1.Interfaces.IDefiner;
import task4_1.Interfaces.Models.BinaryOperation;
import task4_1.Interfaces.Models.Function;

public class Definer implements IDefiner {
    @Override
    public BinaryOperation defineBinaryOperation(String token) {
        return switch (token) {
            case "+" -> BinaryOperation.Addition;
            case "-" -> BinaryOperation.Subtraction;
            case "*" -> BinaryOperation.Multiplication;
            case "/" -> BinaryOperation.Division;
            default -> null;
        };
    }

    @Override
    public Function defineFunction(String token) {
        return switch (token) {
            case "sin" -> Function.Sin;
            case "cos" -> Function.Cos;
            case "log" -> Function.Log;
            case "sqrt" -> Function.Sqrt;
            default -> null;
        };
    }
}
