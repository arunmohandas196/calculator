package com.airwallex.calculator.application.service.operator;

import com.airwallex.calculator.domain.expression.operator.Operator;
import com.airwallex.calculator.domain.expression.operator.OperatorType;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;

public abstract class RPNOperator extends Operator<RPNExpression, RPNExpression> {
    public RPNOperator(String notation, OperatorType operatorType) {
        super(notation, operatorType);
    }


    public abstract RPNExpression apply(RPNExpression expression);

}
