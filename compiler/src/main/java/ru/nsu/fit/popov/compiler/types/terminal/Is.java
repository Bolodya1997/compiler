package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Is extends TerminalType {

    private final static String IS = ":=";

    @Override
    public String getDefaultValue() {
        return IS;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(IS);
    }
}
