package com.airwallex.calculator.application.service;

import com.airwallex.calculator.application.port.in.CalculatorAddElementsCommand;
import com.airwallex.calculator.application.port.in.CalculatorPerformOperationUseCase;
import com.airwallex.calculator.application.service.operator.RPNOperator;
import com.airwallex.calculator.common.UseCase;
import com.airwallex.calculator.domain.exception.OperationException;
import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.operator.Operator;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;
import com.airwallex.calculator.domain.realnumber.RealNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static java.text.MessageFormat.format;

@RequiredArgsConstructor
@UseCase
public class RPNCalculatorService implements CalculatorPerformOperationUseCase {
    RPNExpression rpnExpression;
    RealNumberOperatorFactory realNumberOperatorFactory;
    RPNOperatorFactory rpnOperatorFactory;
    RealNumberOperationService realNumberOperationService;

    @Autowired
    public RPNCalculatorService(RealNumberOperatorFactory realNumberOperatorFactory, RPNOperatorFactory rpnOperatorFactory, RealNumberOperationService realNumberOperationService) {
        this.rpnExpression = new RPNExpression();
        this.realNumberOperatorFactory = realNumberOperatorFactory;
        this.rpnOperatorFactory = rpnOperatorFactory;
        this.realNumberOperationService = realNumberOperationService;
    }

    @Override
    public RPNExpression getCurrentRPNExpression() {
        return rpnExpression;
    }

    @Override
    public RPNExpression addElements(CalculatorAddElementsCommand addElementsCommand) {
        String element;
        for (int i = 0; i < addElementsCommand.getElements().size(); i++) {
            element = addElementsCommand.getElements().get(i);


            if (!performRealNumberOperation(element, i) && !performRPNOperation(element, i)) {
                try {
                    this.rpnExpression.addOperand(new RealNumber(element));
                    rpnExpression.getOperatorHistory().add(null);
                } catch (Exception ex) {
                    throw new OperationException(format("element {0} (position: {1}): Unknown element", addElementsCommand.getElements().get(i), 2 * i + 1, ex.getMessage()), ex);
                }
            }
        }
        return rpnExpression;
    }

    private boolean performRealNumberOperation(String element, int currentIndex) {
        boolean isRealTimeOperation = false;
        Operator<Operand<BigDecimal>, BigDecimal> realNumberOperator = realNumberOperatorFactory.getOperator(element);
        if (realNumberOperator != null) {
            isRealTimeOperation = true;
            try {
                OperationInstruction<Operand<BigDecimal>, BigDecimal> instruction = realNumberOperationService.performOperation(realNumberOperator, rpnExpression);
                rpnExpression.getOperatorHistory().add(instruction);
            } catch (Exception ex) {
                throw new OperationException(format("operator {0} (position: {1}): {2}", realNumberOperator.getNotation(), 2 * currentIndex + 1, ex.getMessage()), ex);
            }
        }
        return isRealTimeOperation;

    }

    private boolean performRPNOperation(String element, int currentIndex) {
        boolean isRpnOperation = false;
        RPNOperator rpnOperator = rpnOperatorFactory.getRPNOperator(element);
        if (rpnOperator != null) {
            isRpnOperation = true;
            try {
                rpnOperatorFactory.performOperation(rpnOperator, rpnExpression);
            } catch (Exception ex) {
                throw new OperationException(format("operator {0} (position: {1}): {2}", rpnOperator.getNotation(), 2 * currentIndex + 1, ex.getMessage()), ex);
            }
        }
        return isRpnOperation;

    }


}
