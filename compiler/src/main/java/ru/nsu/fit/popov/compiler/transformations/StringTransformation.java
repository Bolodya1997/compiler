package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PushStringRef;
import ru.nsu.fit.popov.compiler.types.terminal.StringConstant;

public class StringTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof StringConstant))
            return node;

        Stack.putDepth((short) 1);

        String string = node.toString();
        string = string.substring(1, string.length() - 1);

        return new CompiledNode(new PushStringRef(string));
    }
}
