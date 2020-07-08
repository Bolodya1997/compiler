package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class New extends TerminalType {

    private final static String NEW = "NEW";

    @Override
    public String getDefaultValue() {
        return NEW;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(NEW);
    }
}
