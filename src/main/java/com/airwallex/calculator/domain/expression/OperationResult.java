package com.airwallex.calculator.domain.expression;

import lombok.Value;

@Value
public class OperationResult<E extends Element, V> {
    OperationInstruction<E, V> operationInstruction;
    V result;
}
