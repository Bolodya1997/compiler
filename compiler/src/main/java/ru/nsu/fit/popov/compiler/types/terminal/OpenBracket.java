package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class OpenBracket extends TerminalType {

    private final static String OPEN_BRACKET = "[";

    @Override
    public String getDefaultValue() {
        return OPEN_BRACKET;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(OPEN_BRACKET);
    }
}
