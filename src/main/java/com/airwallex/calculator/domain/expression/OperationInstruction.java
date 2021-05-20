package com.airwallex.calculator.domain.expression;

import com.airwallex.calculator.domain.expression.operator.Operator;
import lombok.Value;

import java.util.List;

@Value
public class OperationInstruction<E extends Element, V> {
    List<Operand<V>> operands;
    Operator<E, V> operator;

    public OperationInstruction(Operator<E, V> operator, List<Operand<V>> operands) {
        this.operands = operands;
        this.operator = operator;
    }
}
