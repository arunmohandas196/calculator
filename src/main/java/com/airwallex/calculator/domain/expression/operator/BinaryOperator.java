package com.airwallex.calculator.domain.expression.operator;

import com.airwallex.calculator.domain.expression.Element;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.OperationResult;

import java.util.function.BiFunction;

public class BinaryOperator<E extends Element, V> extends Operator<E, V> {
    protected BiFunction<V, V, V> biFunction;

    public BinaryOperator(String notation, Operator<E, V> inverseOperator) {
        super(notation, OperatorType.Binary, inverseOperator);
    }

    private V apply(V left, V right) {
        return biFunction.apply(left, right);
    }

    @Override
    public OperationResult<E, V> apply(OperationInstruction<E, V> operationInstruction) {
        V output = apply(operationInstruction.getOperands().get(0).getValue(), operationInstruction.getOperands().get(1).getValue());
        return new OperationResult<E, V>(operationInstruction, output);
    }
}
