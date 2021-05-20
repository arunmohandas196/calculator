package com.airwallex.calculator.domain.realnumber;

import com.airwallex.calculator.domain.exception.ElementParsingException;
import com.airwallex.calculator.domain.expression.Operand;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.text.MessageFormat.format;

public class RealNumber extends Operand<BigDecimal> {
    public static final int SCALE = 15;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    public static final MathContext mc = new MathContext(100);

    public RealNumber(String value) {
        super(value);
    }

    public RealNumber(BigDecimal value) {
        super(value);
    }

    @Override
    protected BigDecimal parseValue(String value) throws ElementParsingException {
        try {
            return new BigDecimal(value, mc).setScale(SCALE, ROUNDING_MODE);
        } catch (NumberFormatException nfe) {
            throw new ElementParsingException(format("Unable to parse input {0} to real number", value), nfe);
        }
    }
}
