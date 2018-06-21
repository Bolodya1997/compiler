package ru.nsu.fit.popov.ast.types;

import org.jetbrains.annotations.NotNull;

public abstract class TerminalType extends Type {

    public abstract String getDefaultValue();

    public abstract boolean isCorrect(@NotNull String value);
}
