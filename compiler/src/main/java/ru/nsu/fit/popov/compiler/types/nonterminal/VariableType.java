package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.IntegerType;
import ru.nsu.fit.popov.compiler.types.terminal.RefType;

public class VariableType extends NonTerminalType {

    public VariableType() {
        final VariableType type = (VariableType) BASE_INSTANCES.get(VariableType.class);
        if (type == null)
            return;

        rules = new Type[][]{
                { new IntegerType() },
                { new RefType() }
        };

        type.rules = rules;
    }
}
