package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Invoke extends TerminalType {

    private final static String INVOKE = "INVOKE";

    @Override
    public String getDefaultValue() {
        return INVOKE;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(INVOKE);
    }
}
