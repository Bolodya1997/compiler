package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class False extends TerminalType {

    private final static String FALSE = "FALSE";

    @Override
    public String getDefaultValue() {
        return FALSE;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(FALSE);
    }
}
