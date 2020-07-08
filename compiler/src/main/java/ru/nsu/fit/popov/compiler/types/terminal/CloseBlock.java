package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class CloseBlock extends TerminalType {

    private final static String CLOSE_BLOCK = "}";

    @Override
    public String getDefaultValue() {
        return CLOSE_BLOCK;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(CLOSE_BLOCK);
    }
}
