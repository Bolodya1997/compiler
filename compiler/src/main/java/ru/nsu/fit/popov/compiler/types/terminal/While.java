package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class While extends TerminalType {

    private final static String WHILE = "WHILE";

    @Override
    public String getDefaultValue() {
        return WHILE;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(WHILE);
    }
}
