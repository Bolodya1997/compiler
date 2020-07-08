package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Plus extends TerminalType {

    private final static String PLUS = "+";

    @Override
    public String getDefaultValue() {
        return PLUS;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(PLUS);
    }
}
