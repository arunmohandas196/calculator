package com.airwallex.calculator.domain.expression.operator;

import com.airwallex.calculator.domain.expression.Element;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.OperationResult;

import java.util.function.Function;

public class UnaryOperator<E extends Element, V> extends Operator<E, V> {
    protected Function<V, V> unaryFunction;

    public UnaryOperator(String notation, Operator<E, V> inverseOperator) {
        super(notation, OperatorType.Unary, inverseOperator);
    }

    public V apply(V unaryOperand) {
        return unaryFunction.apply(unaryOperand);
    }

    @Override
    public OperationResult<E, V> apply(OperationInstruction<E, V> operationInstruction) {
        V output = apply(operationInstruction.getOperands().get(0).getValue());
        return new OperationResult<>(operationInstruction, output);
    }
}
