package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.LineEnd;

public class Root extends NonTerminalType {

    public Root() {
        final Root root = (Root) BASE_INSTANCES.get(Root.class);
        if (root == null)
            return;

        final Type program = BASE_INSTANCES.get(Program.class);
        rules = new Type[][]{
                { new LineEnd(), program }
        };

        root.rules = rules;
    }
}
