package com.airwallex.calculator.application.port.in;

import com.airwallex.calculator.domain.expression.rpn.RPNExpression;

public interface CalculatorPerformOperationUseCase {

    RPNExpression getCurrentRPNExpression();

    RPNExpression addElements(CalculatorAddElementsCommand addElementsCommand);
}
