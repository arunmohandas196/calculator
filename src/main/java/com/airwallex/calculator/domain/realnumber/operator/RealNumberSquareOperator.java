package com.airwallex.calculator.domain.realnumber.operator;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.operator.UnaryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RealNumberSquareOperator extends UnaryOperator<Operand<BigDecimal>, BigDecimal> {
    @Autowired
    public RealNumberSquareOperator(RealNumberSquareRootOperator realNumberSquareRootOperator) {
        super(RealNumberOperatorNotationConstants.SQUARE_NOTATION, realNumberSquareRootOperator);
        this.unaryFunction = value -> value.multiply(value);
    }
}
