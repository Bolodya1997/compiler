package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.*;

public class DoPrint extends NonTerminalType {

    public DoPrint() {
        final DoPrint doPrint = (DoPrint) BASE_INSTANCES.get(DoPrint.class);
        if (doPrint == null)
            return;

        final Print print = new Print();
        final OpenBracket ob = new OpenBracket();
        final CloseBracket cb = new CloseBracket();
        final Type integer = BASE_INSTANCES.get(IntegerValue.class);
        rules = new Type[][]{
                { print, ob, new StringConstant(), cb },
                { print, ob, new Variable(), cb },
                { print, ob, integer, cb },
                { print, ob, cb }
        };

        doPrint.rules = rules;
    }
}
