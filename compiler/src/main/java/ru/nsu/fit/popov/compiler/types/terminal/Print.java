package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Print extends TerminalType {

    private final static String PRINT = "PRINT";

    @Override
    public String getDefaultValue() {
        return PRINT;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(PRINT);
    }
}
