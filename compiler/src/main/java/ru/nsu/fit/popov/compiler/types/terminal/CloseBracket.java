package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class CloseBracket extends TerminalType {

    private final static String CLOSE_BRACKET = "]";

    @Override
    public String getDefaultValue() {
        return CLOSE_BRACKET;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(CLOSE_BRACKET);
    }
}
