package com.airwallex.calculator.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public
class CalculatorAddElementsCommand {
    List<String> elements;
}
