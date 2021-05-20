package com.airwallex.calculator.application.service;

import com.airwallex.calculator.adapter.in.RPNCalculatorConsole;
import com.airwallex.calculator.application.service.operator.RPNOperatorConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static com.airwallex.calculator.application.service.operator.RPNOperatorConstants.CLEAR;
import static com.airwallex.calculator.application.service.operator.RPNOperatorConstants.UNDO;
import static com.airwallex.calculator.domain.realnumber.operator.RealNumberOperatorNotationConstants.*;

@SpringBootTest
public class RPNOperatorFactoryTest {
    @Autowired
    RPNOperatorFactory rpnOperatorFactory;

    @MockBean
    RPNCalculatorConsole rpnCalculatorConsole;

    @Test
    void shouldLoadAllRealNumberOperators() {
        List<String> notations = Arrays.asList(CLEAR, UNDO);
        notations.forEach(notation-> Assertions.assertNotNull(rpnOperatorFactory.getRPNOperator((notation))));
    }

}
