package com.airwallex.calculator.domain.realnumber.operator;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.operator.BinaryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RealNumberMultiplicationOperator extends BinaryOperator<Operand<BigDecimal>, BigDecimal> {
    @Autowired
    public RealNumberMultiplicationOperator(@Lazy RealNumberDivisionOperator realNumberDivisionOperator) {
        super(RealNumberOperatorNotationConstants.MULTIPLICATION_NOTATION, realNumberDivisionOperator);
        this.biFunction = BigDecimal::multiply;
    }
}
