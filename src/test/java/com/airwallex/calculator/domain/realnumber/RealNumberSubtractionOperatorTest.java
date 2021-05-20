package com.airwallex.calculator.domain.realnumber;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.operator.Operator;
import com.airwallex.calculator.domain.realnumber.operator.RealNumberSubtractionOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RealNumberSubtractionOperatorTest {
    Operator<Operand<BigDecimal>,BigDecimal> realNumberSubtractionOperator = new RealNumberSubtractionOperator(null);

    @Test
    public void shouldSubtractNumbersCorrectly() {
        List<Operand<BigDecimal>> operands= Arrays.asList(new RealNumber("1.500"),new RealNumber("0.500"));
        OperationInstruction<Operand<BigDecimal>,BigDecimal> instruction = new OperationInstruction<>(realNumberSubtractionOperator,operands);
        Assertions.assertEquals(new RealNumber("1.000").getValue(), realNumberSubtractionOperator.apply(instruction).getResult());
    }

}
