package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.*;

public class DoInvoke extends NonTerminalType {

    public DoInvoke() {
        final DoInvoke doInvoke = (DoInvoke) BASE_INSTANCES.get(DoInvoke.class);
        if (doInvoke == null)
            return;

        final Variable variable = new Variable();
        final Invoke invoke = new Invoke();
        final OpenBracket ob = new OpenBracket();
        final StringConstant className = new StringConstant();
        final StringConstant methodName = className;
        final StringConstant type = className;
        final CloseBracket cb = new CloseBracket();
        final Type arguments = BASE_INSTANCES.get(Arguments.class);
        rules = new Type[][]{
                { variable, invoke, ob, className, methodName, type, cb },
                { variable, invoke, ob, className, methodName, type, arguments, cb }
        };

        doInvoke.rules = rules;
    }
}
