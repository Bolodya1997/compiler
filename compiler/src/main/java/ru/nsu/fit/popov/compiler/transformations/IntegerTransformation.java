package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PushIntValue;
import ru.nsu.fit.popov.compiler.types.terminal.IntegerConstant;

public class IntegerTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof IntegerConstant))
            return node;

        Stack.putDepth((short) 1);

        int value = Integer.decode(node.toString());

        return new CompiledNode(new PushIntValue(value));
    }
}
