package task4_1.Interfaces;

import task4_1.Interfaces.Models.BinaryOperation;
import task4_1.Interfaces.Models.Function;

public interface IDefiner {
    BinaryOperation defineBinaryOperation(String token);
    Function defineFunction(String token);
}
