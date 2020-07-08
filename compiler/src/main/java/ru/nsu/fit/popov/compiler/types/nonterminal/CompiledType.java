package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.TerminalType;

public class CompiledType extends TerminalType {

    public static CompiledType type = new CompiledType();

    @Override
    public String getDefaultValue() {
        return " ";
    }

    @Override
    public boolean isCorrect(String value) {
        return false;
    }
}
