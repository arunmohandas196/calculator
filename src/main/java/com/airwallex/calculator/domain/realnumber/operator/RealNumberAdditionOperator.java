package com.airwallex.calculator.domain.realnumber.operator;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.operator.BinaryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RealNumberAdditionOperator extends BinaryOperator<Operand<BigDecimal>, BigDecimal> {
    @Autowired
    public RealNumberAdditionOperator(@Lazy RealNumberSubtractionOperator realNumberSubtractionOperator) {
        super(RealNumberOperatorNotationConstants.ADDITION_NOTATION, realNumberSubtractionOperator);
        this.biFunction = BigDecimal::add;
    }
}
