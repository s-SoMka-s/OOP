package task4_1.Implementations;

import org.jetbrains.annotations.Nullable;
import task4_1.Interfaces.IApplier;
import task4_1.Interfaces.Models.Function;
import task4_1.Interfaces.Models.BinaryOperation;

public class Applier implements IApplier {
    @Override
    public double applyOperation(BinaryOperation binaryOperation, double arg1, double arg2) {
        double res;

        switch (binaryOperation){
            case Addition:
                res = arg1 + arg2;
                break;
            case Subtraction:
                res = arg1 - arg2;
                break;
            case Multiplication:
                res = arg1*arg2;
                break;
            case Division:
                res = arg1 / arg2;
                break;
            default:
                throw new IllegalStateException("Operation is not supported!");
        }

        return res;
    }

    @Override
    public double applyFunction(Function function, double arg) {
        double res;

        switch (function){
            case Sin:
                res = Math.sin(arg);
                break;
            case Cos:
                res = Math.cos(arg);
                break;
            case Log:
                res = Math.log(arg);
                break;
            case Sqrt:
                res = Math.sqrt(arg);
                break;
            default:
                throw new IllegalStateException("Function is not supported!");
        }

        return res;
    }
}
