package com.airwallex.calculator.application.service;

import com.airwallex.calculator.adapter.in.RPNCalculatorConsole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static com.airwallex.calculator.domain.realnumber.operator.RealNumberOperatorNotationConstants.*;

@SpringBootTest
public class RealNumberOperatorFactoryTest {
    @Autowired
    RealNumberOperatorFactory realNumberOperatorFactory;

    @MockBean
    RPNCalculatorConsole rpnCalculatorConsole;

    @Test
    void shouldLoadAllRealNumberOperators() {
        List<String> notations = Arrays.asList(ADDITION_NOTATION, SUBTRACTION_NOTATION, SQUARE_NOTATION, SQUARE_ROOT_NOTATION, MULTIPLICATION_NOTATION, DIVISION_NOTATION);
        notations.forEach(notation-> Assertions.assertNotNull(realNumberOperatorFactory.getOperator(notation)));
    }

}
