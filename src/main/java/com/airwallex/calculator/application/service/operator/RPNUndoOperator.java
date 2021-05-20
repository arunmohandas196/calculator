package com.airwallex.calculator.application.service.operator;

import com.airwallex.calculator.application.service.RealNumberOperationService;
import com.airwallex.calculator.application.service.RealNumberOperatorFactory;
import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.operator.OperatorType;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RPNUndoOperator extends RPNOperator {
    RealNumberOperatorFactory operatorFactory;
    RealNumberOperationService realNumberOperationService;

    public RPNUndoOperator(RealNumberOperatorFactory operatorFactory, RealNumberOperationService realNumberOperationService) {
        super(RPNOperatorConstants.UNDO, OperatorType.Unary);
        this.operatorFactory = operatorFactory;
        this.realNumberOperationService = realNumberOperationService;
    }

    @Override
    public RPNExpression apply(RPNExpression rpnExpression) {
        if (!rpnExpression.getOperatorHistory().isEmpty()) {
            OperationInstruction<Operand<BigDecimal>, BigDecimal> instruction = rpnExpression.getOperatorHistory().pop();
            if (instruction != null) {
                if (instruction.getOperator().getInverseOperator().getInverseOperator().getOperatorType() == OperatorType.Binary) {
                    rpnExpression.getOperandStack().add(instruction.getOperands().get(instruction.getOperands().size() - 1));
                }
                realNumberOperationService.performOperation(instruction.getOperator().getInverseOperator(), rpnExpression);
                rpnExpression.getOperandStack().add(instruction.getOperands().get(instruction.getOperands().size() - 1));
            } else {
                rpnExpression.getOperandStack().pop();
            }
        }
        return rpnExpression;
    }
}
