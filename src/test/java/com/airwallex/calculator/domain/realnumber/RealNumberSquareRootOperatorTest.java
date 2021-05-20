package com.airwallex.calculator.domain.realnumber;

import com.airwallex.calculator.domain.expression.Operand;
import com.airwallex.calculator.domain.expression.OperationInstruction;
import com.airwallex.calculator.domain.realnumber.operator.RealNumberSquareRootOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RealNumberSquareRootOperatorTest {
    RealNumberSquareRootOperator realNumberSquareRootOperator = new RealNumberSquareRootOperator(null);

    @Test
    public void shouldCalculateSquareRootCorrectly() {
        List<Operand<BigDecimal>> operands= Arrays.asList(new RealNumber("4.0"));
        OperationInstruction<Operand<BigDecimal>,BigDecimal> instruction = new OperationInstruction<>(realNumberSquareRootOperator,operands);
        Assertions.assertEquals(new RealNumber("2.0").getValue(), realNumberSquareRootOperator.apply(instruction).getResult());
    }

}
