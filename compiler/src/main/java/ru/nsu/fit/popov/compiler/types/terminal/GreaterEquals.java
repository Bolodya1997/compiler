package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class GreaterEquals extends TerminalType {

    private final static String GREATER_EQUALS = ">=";

    @Override
    public String getDefaultValue() {
        return GREATER_EQUALS;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(GREATER_EQUALS);
    }
}
