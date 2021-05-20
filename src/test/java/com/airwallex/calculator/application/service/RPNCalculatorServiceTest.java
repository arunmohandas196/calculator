package com.airwallex.calculator.application.service;

import com.airwallex.calculator.adapter.in.RPNCalculatorConsole;
import com.airwallex.calculator.application.port.in.CalculatorAddElementsCommand;
import com.airwallex.calculator.domain.realnumber.RealNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

@SpringBootTest
public class RPNCalculatorServiceTest {
    @Autowired
    RealNumberOperatorFactory realNumberOperatorFactory;
    @MockBean
    RPNCalculatorConsole rpnCalculatorConsole;
    @Autowired
    RPNCalculatorService rpnCalculatorService;

    @Test
    void shouldCalculateCorrectly() {
        rpnCalculatorService.addElements(new CalculatorAddElementsCommand(Arrays.asList("1", "2", "+")));
        Assertions.assertEquals(new RealNumber("3").getValue(), rpnCalculatorService.getCurrentRPNExpression().getOperandStack().pop().getValue());

        rpnCalculatorService.addElements(new CalculatorAddElementsCommand(Arrays.asList("2", "sqrt")));
        Assertions.assertEquals(new RealNumber("1.414213562373095").getValue(), rpnCalculatorService.getCurrentRPNExpression().getOperandStack().pop().getValue());

        rpnCalculatorService.addElements(new CalculatorAddElementsCommand(Arrays.asList("20", "5", "*")));
        rpnCalculatorService.addElements(new CalculatorAddElementsCommand(Arrays.asList("undo")));
        Assertions.assertEquals(new RealNumber("5").getValue(), rpnCalculatorService.getCurrentRPNExpression().getOperandStack().pop().getValue());
        Assertions.assertEquals(new RealNumber("20").getValue(), rpnCalculatorService.getCurrentRPNExpression().getOperandStack().pop().getValue());

        rpnCalculatorService.addElements(new CalculatorAddElementsCommand(Arrays.asList("1", "2", "3")));
        rpnCalculatorService.addElements(new CalculatorAddElementsCommand(Arrays.asList("clear")));
        Assertions.assertEquals(0, rpnCalculatorService.getCurrentRPNExpression().getOperandStack().size());


    }
}
