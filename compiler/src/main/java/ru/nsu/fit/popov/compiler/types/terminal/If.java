package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class If extends TerminalType {

    private final static String IF = "IF";

    @Override
    public String getDefaultValue() {
        return IF;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(IF);
    }
}
