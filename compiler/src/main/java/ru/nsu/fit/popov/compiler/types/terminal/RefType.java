package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class RefType extends TerminalType {

    private final static String REF = "REF";

    @Override
    public String getDefaultValue() {
        return REF;
    }

    @Override
    public boolean isCorrect(String value) {
        return value.equals(REF);
    }
}
