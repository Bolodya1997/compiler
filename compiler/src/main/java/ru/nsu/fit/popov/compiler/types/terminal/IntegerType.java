package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class IntegerType extends TerminalType {

    private final static String INT = "INTEGER";

    @Override
    public String getDefaultValue() {
        return INT;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(INT);
    }
}
