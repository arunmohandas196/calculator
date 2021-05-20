package com.airwallex.calculator.application.service;

import com.airwallex.calculator.domain.exception.ElementParsingException;
import com.airwallex.calculator.domain.exception.InvalidOperatorTypeException;
import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.OperationResult;
import com.airwallex.calculator.domain.expression.operator.Operator;
import com.airwallex.calculator.domain.expression.operator.OperatorType;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;
import com.airwallex.calculator.domain.realnumber.RealNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.format;

@Component
public class RealNumberOperationService {

    @Autowired
    RealNumberOperatorFactory realNumberOperatorFactory;

    public OperationInstruction<Operand<BigDecimal>, BigDecimal> performOperation(Operator<Operand<BigDecimal>, BigDecimal> operator, RPNExpression rpnExpression) {
        List<Operand<BigDecimal>> operands = generateOperandsForOperator(operator, rpnExpression);

        OperationInstruction<Operand<BigDecimal>, BigDecimal> instruction = new OperationInstruction<>(operator, operands);
        try {
            OperationResult<Operand<BigDecimal>, BigDecimal> result = realNumberOperatorFactory.performOperation(operator, instruction);
            rpnExpression.addOperand(new RealNumber(result.getResult()));
        } catch (Exception ex) {
            for (Operand<BigDecimal> operand : operands) {
                rpnExpression.getOperandStack().push(operand);
            }
            throw ex;
        }
        return instruction;
    }

    private List<Operand<BigDecimal>> generateOperandsForOperator(Operator<Operand<BigDecimal>, BigDecimal> operator, RPNExpression rpnExpression) {
        List<Operand<BigDecimal>> operands = new ArrayList<>();
        if (operator.getOperatorType() == OperatorType.Unary) {
            generateOperandForUnaryOperator(operands, rpnExpression);
        } else if (operator.getOperatorType() == OperatorType.Binary) {
            generateOperandForBinaryOperator(operands, rpnExpression);
        } else {
            throw new InvalidOperatorTypeException(format("Operator Type {1} is not supported", operator.getOperatorType()));
        }
        return operands;
    }

    private void generateOperandForBinaryOperator(List<Operand<BigDecimal>> operands, RPNExpression rpnExpression) {
        if (rpnExpression.getOperandStack().size() < 2) {
            throw new ElementParsingException("Insufficient parameters");
        }
        Operand<BigDecimal> secondOperand = rpnExpression.removeTopOperand();
        Operand<BigDecimal> firstOperand = rpnExpression.removeTopOperand();
        operands.add(firstOperand);
        operands.add(secondOperand);
    }

    private void generateOperandForUnaryOperator(List<Operand<BigDecimal>> operands, RPNExpression rpnExpression) {
        if (rpnExpression.getOperandStack().size() == 0) {
            throw new ElementParsingException("Insufficient parameters");
        }
        operands.add(rpnExpression.removeTopOperand());
    }
}
