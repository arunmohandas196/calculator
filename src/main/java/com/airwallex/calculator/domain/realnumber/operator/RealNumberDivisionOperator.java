package com.airwallex.calculator.domain.realnumber.operator;

import com.airwallex.calculator.domain.exception.OperationException;
import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.operator.BinaryOperator;
import com.airwallex.calculator.domain.realnumber.RealNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Component
public class RealNumberDivisionOperator extends BinaryOperator<Operand<BigDecimal>, BigDecimal> {
    @Autowired
    public RealNumberDivisionOperator(RealNumberMultiplicationOperator realNumberMultiplicationOperator) {
        super(RealNumberOperatorNotationConstants.DIVISION_NOTATION, realNumberMultiplicationOperator);
        this.biFunction = getDivisionFunction();
    }

    private BiFunction<BigDecimal, BigDecimal, BigDecimal> getDivisionFunction() {

        return (left, right) -> {
            if (right.compareTo(BigDecimal.ZERO) == 0) {
                throw new OperationException("Division by 0 is mot allowed");
            }
            return left.divide(right, RealNumber.SCALE, RealNumber.ROUNDING_MODE);
        };
    }
}
