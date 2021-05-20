package com.airwallex.calculator.application.service.operator;

import com.airwallex.calculator.domain.expression.operator.OperatorType;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;
import org.springframework.stereotype.Component;

@Component
public class RPNClearOperator extends RPNOperator {


    public RPNClearOperator() {
        super(RPNOperatorConstants.CLEAR, OperatorType.Unary);
    }

    @Override
    public RPNExpression apply(RPNExpression rpnExpression) {
        rpnExpression.getOperandStack().clear();
        rpnExpression.getOperatorHistory().clear();
        return rpnExpression;
    }
}
