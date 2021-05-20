package com.airwallex.calculator.domain.realnumber.operator;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.operator.BinaryOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RealNumberSubtractionOperator extends BinaryOperator<Operand<BigDecimal>, BigDecimal> {
    @Autowired
    public RealNumberSubtractionOperator(RealNumberAdditionOperator realNumberAdditionOperator) {
        super(RealNumberOperatorNotationConstants.SUBTRACTION_NOTATION, realNumberAdditionOperator);
        this.biFunction = BigDecimal::subtract;
    }
}
