package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Comma extends TerminalType {

    private final static String COMMA = ",";

    @Override
    public String getDefaultValue() {
        return COMMA;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(COMMA);
    }
}
