package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.*;

public class Condition extends NonTerminalType {

    public Condition() {
        final Condition condition = (Condition) BASE_INSTANCES.get(Condition.class);
        if (condition == null)
            return;

        final Type value = BASE_INSTANCES.get(IntegerValue.class);
        rules = new Type[][]{
                { new True() },
                { new False() },
                { value, new Equals(), value },
                { value, new NotEquals(), value },
                { value, new GreaterEquals(), value }
        };

        condition.rules = rules;
    }
}
