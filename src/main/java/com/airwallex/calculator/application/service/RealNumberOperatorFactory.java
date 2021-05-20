package com.airwallex.calculator.application.service;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.OperationResult;
import com.airwallex.calculator.domain.expression.operator.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RealNumberOperatorFactory {
    private final Map<String, Operator<Operand<BigDecimal>, BigDecimal>> mapOfNotationToOperators;

    @Autowired
    public RealNumberOperatorFactory(List<Operator<Operand<BigDecimal>, BigDecimal>> operators) {
        this.mapOfNotationToOperators = generateMapOfOperators(operators);
    }

    private Map<String, Operator<Operand<BigDecimal>, BigDecimal>> generateMapOfOperators(List<Operator<Operand<BigDecimal>, BigDecimal>> operators) {
        return operators.stream().collect(Collectors.toMap(Operator::getNotation, Function.identity()));
    }

    public OperationResult<Operand<BigDecimal>, BigDecimal> performOperation(Operator<Operand<BigDecimal>, BigDecimal> operator, OperationInstruction<Operand<BigDecimal>, BigDecimal> instruction) {
        return operator.apply(instruction);
    }

    public Operator<Operand<BigDecimal>, BigDecimal> getOperator(String operatorNotation) {
        return mapOfNotationToOperators.get(operatorNotation);
    }

}
