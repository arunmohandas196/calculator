package com.airwallex.calculator.application.service;

import com.airwallex.calculator.application.service.operator.RPNOperator;
import com.airwallex.calculator.domain.expression.operator.Operator;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RPNOperatorFactory {
    private final Map<String, RPNOperator> mapOfNotationToRPNOperators;

    @Autowired
    public RPNOperatorFactory(List<RPNOperator> operators) {
        this.mapOfNotationToRPNOperators = generateMapOfRPNOperators(operators);
    }

    private Map<String, RPNOperator> generateMapOfRPNOperators(List<RPNOperator> operators) {
        return operators.stream().collect(Collectors.toMap(Operator::getNotation, Function.identity()));
    }

    public RPNExpression performOperation(RPNOperator operator, RPNExpression expression) {
        return operator.apply(expression);
    }

    public RPNOperator getRPNOperator(String operatorNotation) {
        return mapOfNotationToRPNOperators.get(operatorNotation);
    }

}
