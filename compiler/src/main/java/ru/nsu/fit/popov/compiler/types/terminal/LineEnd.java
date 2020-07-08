package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class LineEnd extends TerminalType {

    private final static String LINE_END = "#";

    @Override
    public String getDefaultValue() {
        return LINE_END;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(LINE_END);
    }
}
