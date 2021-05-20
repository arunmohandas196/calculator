package com.airwallex.calculator.domain.realnumber.operator;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.operator.UnaryOperator;
import com.airwallex.calculator.domain.realnumber.RealNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RealNumberSquareRootOperator extends UnaryOperator<Operand<BigDecimal>, BigDecimal> {
    @Autowired
    public RealNumberSquareRootOperator(@Lazy RealNumberSquareOperator realNumberSquareOperator) {
        super(RealNumberOperatorNotationConstants.SQUARE_ROOT_NOTATION, realNumberSquareOperator);
        this.unaryFunction = (number) -> number.sqrt(RealNumber.mc).setScale(RealNumber.SCALE, RealNumber.ROUNDING_MODE);
    }
}
