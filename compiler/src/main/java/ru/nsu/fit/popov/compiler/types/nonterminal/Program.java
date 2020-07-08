package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.LineEnd;

public class Program extends NonTerminalType {

    public Program() {
        final Program program = (Program) BASE_INSTANCES.get(Program.class);
        if (program == null)
            return;

        final Type line = BASE_INSTANCES.get(Line.class);
        final Type block = BASE_INSTANCES.get(Block.class);
        final Type ifCondition = BASE_INSTANCES.get(IfCondition.class);
        final Type whileCondition = BASE_INSTANCES.get(WhileCondition.class);
        rules = new Type[][]{
                { line },
                { block },
                { ifCondition },
                { whileCondition },
                { program, line },
                { program, block },
                { program, ifCondition },
                { program, whileCondition }
        };

        program.rules = rules;
    }
}
