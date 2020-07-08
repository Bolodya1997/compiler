package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.Comma;
import ru.nsu.fit.popov.compiler.types.terminal.Variable;

public class Declaration extends NonTerminalType {

    public Declaration() {
        final Declaration declaration = (Declaration) BASE_INSTANCES.get(Declaration.class);
        if (declaration == null)
            return;

        final Type type = BASE_INSTANCES.get(VariableType.class);
        final Variable variable = new Variable();
        rules = new Type[][]{
                { type, variable },
                { declaration, new Comma(), variable }
        };

        declaration.rules = rules;
    }
}
