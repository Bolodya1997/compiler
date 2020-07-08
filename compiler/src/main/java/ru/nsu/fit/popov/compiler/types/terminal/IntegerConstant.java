package ru.nsu.fit.popov.compiler.types.terminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class IntegerConstant extends TerminalType {

    @Override
    public String getDefaultValue() {
        return " ";
    }

    @Override
    public boolean isCorrect(String value) {
        return value.chars().allMatch(Character::isDigit);
    }
}
