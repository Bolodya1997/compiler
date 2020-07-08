package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class True extends TerminalType {

    private final static String TRUE = "TRUE";

    @Override
    public String getDefaultValue() {
        return TRUE;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(TRUE);
    }
}
