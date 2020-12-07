package task4_1.Interfaces;

import org.jetbrains.annotations.Nullable;
import task4_1.Interfaces.Models.Function;
import task4_1.Interfaces.Models.BinaryOperation;

public interface IApplier {
    double applyOperation(BinaryOperation binaryOperation, double arg1, @Nullable Double arg2);

    double applyFunction(Function function, double arg);
}
