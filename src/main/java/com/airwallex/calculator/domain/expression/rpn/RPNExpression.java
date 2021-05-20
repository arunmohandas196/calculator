package com.airwallex.calculator.domain.expression.rpn;

import com.airwallex.calculator.domain.expression.Element;
import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Stack;

@EqualsAndHashCode(callSuper = true)
@Value
public class RPNExpression extends Element {
    Stack<Operand<BigDecimal>> operandStack = new Stack<>();
    Stack<OperationInstruction<Operand<BigDecimal>, BigDecimal>> operatorHistory = new Stack<>();

    public Operand<BigDecimal> removeTopOperand() {
        return operandStack.pop();
    }

    public Operand<BigDecimal> addOperand(Operand<BigDecimal> operand) {
        return operandStack.push(operand);
    }
}
