package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Else extends TerminalType {

    private final static String ELSE = "ELSE";

    @Override
    public String getDefaultValue() {
        return ELSE;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(ELSE);
    }
}
