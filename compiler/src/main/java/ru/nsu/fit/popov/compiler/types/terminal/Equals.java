package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Equals extends TerminalType {

    private final static String EQUALS = "==";

    @Override
    public String getDefaultValue() {
        return EQUALS;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(EQUALS);
    }
}
