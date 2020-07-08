package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.CloseBracket;
import ru.nsu.fit.popov.compiler.types.terminal.New;
import ru.nsu.fit.popov.compiler.types.terminal.OpenBracket;
import ru.nsu.fit.popov.compiler.types.terminal.StringConstant;

public class DoNew extends NonTerminalType {

    public DoNew() {
        final DoNew doNew = (DoNew) BASE_INSTANCES.get(DoNew.class);
        if (doNew == null)
            return;

        final New __new = new New();
        final OpenBracket ob = new OpenBracket();
        final StringConstant className = new StringConstant();
        final StringConstant type = className;
        final Type arguments = BASE_INSTANCES.get(Arguments.class);
        final CloseBracket cb = new CloseBracket();
        rules = new Type[][]{
                { __new, ob, className, type, cb },
                { __new, ob, className, type, arguments, cb }
        };

        doNew.rules = rules;
    }
}
