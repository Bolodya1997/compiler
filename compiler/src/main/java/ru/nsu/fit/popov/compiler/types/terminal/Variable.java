package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class Variable extends TerminalType {

    @Override
    public String getDefaultValue() {
        return "variable";
    }

    @Override
    public boolean isCorrect(String value) {
        return Character.isLowerCase(value.charAt(0)) &&
                value.chars().allMatch(ch -> Character.isLowerCase(ch) || Character.isDigit(ch));
    }
}
