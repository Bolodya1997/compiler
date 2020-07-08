package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.CloseBlock;
import ru.nsu.fit.popov.compiler.types.terminal.OpenBlock;

public class Block extends NonTerminalType {

    public Block() {
        final Block block = (Block) BASE_INSTANCES.get(Block.class);
        if (block == null)
            return;

        final Type program = BASE_INSTANCES.get(Program.class);
        rules = new Type[][]{
                { new OpenBlock(), program, new CloseBlock() },
        };

        block.rules = rules;
    }
}
