package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class NotEquals extends TerminalType {

    private final static String NOT_EQUALS = "!=";

    @Override
    public String getDefaultValue() {
        return NOT_EQUALS;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(NOT_EQUALS);
    }
}
