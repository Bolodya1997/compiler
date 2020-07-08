package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.CloseBracket;
import ru.nsu.fit.popov.compiler.types.terminal.OpenBracket;
import ru.nsu.fit.popov.compiler.types.terminal.While;

public class WhileCondition extends NonTerminalType {

    public WhileCondition() {
        final WhileCondition whileCondition =
                (WhileCondition) BASE_INSTANCES.get(WhileCondition.class);
        if (whileCondition == null)
            return;

        final While __while = new While();
        final OpenBracket ob = new OpenBracket();
        final Type condition = BASE_INSTANCES.get(Condition.class);
        final CloseBracket cb = new CloseBracket();
        final Type line = BASE_INSTANCES.get(Line.class);
        final Type block = BASE_INSTANCES.get(Block.class);
        final Type ifCondition = BASE_INSTANCES.get(IfCondition.class);
        rules = new Type[][]{
                { __while, ob, condition, cb, line },
                { __while, ob, condition, cb, block },
                { __while, ob, condition, cb, ifCondition },
                { __while, ob, condition, cb, whileCondition }
        };

        whileCondition.rules = rules;
    }
}
