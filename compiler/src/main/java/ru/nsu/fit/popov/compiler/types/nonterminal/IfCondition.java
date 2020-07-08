package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.CloseBracket;
import ru.nsu.fit.popov.compiler.types.terminal.Else;
import ru.nsu.fit.popov.compiler.types.terminal.If;
import ru.nsu.fit.popov.compiler.types.terminal.OpenBracket;

public class IfCondition extends NonTerminalType {

    public IfCondition() {
        final IfCondition ifCondition = (IfCondition) BASE_INSTANCES.get(IfCondition.class);
        if (ifCondition == null)
            return;

        final If __if = new If();
        final OpenBracket ob = new OpenBracket();
        final Type condition = BASE_INSTANCES.get(Condition.class);
        final CloseBracket cb = new CloseBracket();
        final Type line = BASE_INSTANCES.get(Line.class);
        final Type block = BASE_INSTANCES.get(Block.class);
        final Type whileCondition = BASE_INSTANCES.get(WhileCondition.class);
        final Else __else = new Else();
        rules = new Type[][]{
                { __if, ob, condition, cb, line },
                { __if, ob, condition, cb, block },
                { __if, ob, condition, cb, ifCondition },
                { __if, ob, condition, cb, whileCondition },
                { ifCondition, __else, line },
                { ifCondition, __else, block },
                { ifCondition, __else, ifCondition },
                { ifCondition, __else, whileCondition }
        };

        ifCondition.rules = rules;
    }
}
