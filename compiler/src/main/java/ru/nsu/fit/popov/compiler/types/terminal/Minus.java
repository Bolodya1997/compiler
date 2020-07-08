package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Minus extends TerminalType {

    private final static String MINUS = "-";

    @Override
    public String getDefaultValue() {
        return MINUS;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(MINUS);
    }
}
