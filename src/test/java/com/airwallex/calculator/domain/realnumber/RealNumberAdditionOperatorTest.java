package com.airwallex.calculator.domain.realnumber;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.expression.operator.Operator;
import com.airwallex.calculator.domain.realnumber.operator.RealNumberAdditionOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RealNumberAdditionOperatorTest {
    Operator<Operand<BigDecimal>,BigDecimal> realNumberAdditionOperator = new RealNumberAdditionOperator(null);

    @Test
    public void shouldAddNumbersCorrectly() {
        List<Operand<BigDecimal>> operands= Arrays.asList(new RealNumber("1.500"),new RealNumber("2.510"));
        OperationInstruction<Operand<BigDecimal>,BigDecimal> instruction = new OperationInstruction<>(realNumberAdditionOperator,operands);
        Assertions.assertEquals(new RealNumber("4.010").getValue(), realNumberAdditionOperator.apply(instruction).getResult());
    }

}
