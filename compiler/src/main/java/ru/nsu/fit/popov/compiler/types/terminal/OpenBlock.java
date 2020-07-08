package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class OpenBlock extends TerminalType {

    private final static String OPEN_BLOCK = "{";

    @Override
    public String getDefaultValue() {
        return OPEN_BLOCK;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(OPEN_BLOCK);
    }
}
