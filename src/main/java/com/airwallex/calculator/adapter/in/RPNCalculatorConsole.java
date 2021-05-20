package com.airwallex.calculator.adapter.in;

import com.airwallex.calculator.application.port.in.CalculatorAddElementsCommand;
import com.airwallex.calculator.application.port.in.CalculatorPerformOperationUseCase;
import com.airwallex.calculator.common.ConsoleAdapter;
import com.airwallex.calculator.domain.expression.rpn.RPNExpression;
import com.airwallex.calculator.domain.realnumber.RealNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ConsoleAdapter
public class RPNCalculatorConsole implements CommandLineRunner {
    private static final String ELEMENT_DELIMITER = " ";
    CalculatorPerformOperationUseCase calculatorPerformOperationUseCase;
    BufferedReader reader;
    DecimalFormat df;

    @Autowired
    public RPNCalculatorConsole(CalculatorPerformOperationUseCase calculatorPerformOperationUseCase) {
        this.calculatorPerformOperationUseCase = calculatorPerformOperationUseCase;
        reader = new BufferedReader(new InputStreamReader(System.in));
        setDecimalFormat();
    }

    private void setDecimalFormat() {
        df = new DecimalFormat();
        df.setMaximumFractionDigits(10);
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            String input = reader.readLine();
            List<String> elements = Arrays.asList(input.split(ELEMENT_DELIMITER));
            try {

                this.calculatorPerformOperationUseCase.addElements(new CalculatorAddElementsCommand(elements));
            } catch (Exception oex) {
                System.out.println(oex.getMessage());
            }
            printOperands(this.calculatorPerformOperationUseCase.getCurrentRPNExpression());
        }

    }


    private void printOperands(RPNExpression result) {
        System.out.println("stack: " + result.getOperandStack().stream()
                .map(operand -> df.format(operand.getValue().setScale(10, RealNumber.ROUNDING_MODE)))
                .collect(Collectors.joining(ELEMENT_DELIMITER)));
    }
}
