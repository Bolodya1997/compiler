package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class StringConstant extends TerminalType {

    @Override
    public String getDefaultValue() {
        return "\"string\"";
    }

    @Override
    public boolean isCorrect(String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }
}
