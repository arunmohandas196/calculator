package com.airwallex.calculator.domain.expression.operator;

import com.airwallex.calculator.domain.expression.Element;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.OperationResult;

public abstract class Operator<E extends Element, V> extends Element {
    private final String notation;
    private final OperatorType operatorType;
    private Operator<E, V> inverseOperator;

    public Operator(String notation, OperatorType operatorType, Operator<E, V> inverseOperator) {
        this.notation = notation;
        this.operatorType = operatorType;
        this.inverseOperator = inverseOperator;
    }

    public Operator(String notation, OperatorType operatorType) {
        this.notation = notation;
        this.operatorType = operatorType;
    }

    public String getNotation() {
        return notation;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public Operator<E, V> getInverseOperator() {
        return inverseOperator;
    }

    public OperationResult<E, V> apply(OperationInstruction<E, V> operationInstruction) {
        return null;
    }
}
