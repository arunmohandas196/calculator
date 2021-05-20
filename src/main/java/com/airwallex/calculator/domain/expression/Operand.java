package com.airwallex.calculator.domain.expression;

import com.airwallex.calculator.domain.exception.ElementParsingException;

import java.util.Objects;

public abstract class Operand<E> extends Element {
    protected E value;

    public Operand(E value) {
        this.value = value;
    }

    public Operand(String value) {
        this.value = parseValue(value);
    }

    protected abstract E parseValue(String value) throws ElementParsingException;

    public E getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operand<?> operand = (Operand<?>) o;
        return Objects.equals(value, operand.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Operand{" +
                "value=" + value +
                '}';
    }
}
