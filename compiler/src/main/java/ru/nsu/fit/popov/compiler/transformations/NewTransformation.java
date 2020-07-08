package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.terminals.InvokeSpecial;
import ru.nsu.fit.popov.compiler.bytecode.terminals.NewObj;
import ru.nsu.fit.popov.compiler.types.nonterminal.DoNew;

import java.util.List;

public class NewTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof DoNew))
            return node;

        final CompiledNode compiled = new CompiledNode();

        final String className = node.getChildren().get(2)
                .toString().replaceAll("\"", "");
        final short classRef = ConstantPool.getClassRef(className);

        final List<Node> children = compiled.getChildren();
        children.add(new CompiledNode(new NewObj(classRef)));

        short stackDepth = 2;
        if (node.getChildren().size() == 6) {
            final Node argumentsNode = node.getChildren().get(4);
            children.add(argumentsNode);
            stackDepth += ArgumentCounter.getCount(argumentsNode);
        }

        Stack.putDepth(stackDepth);

        final String type = node.getChildren().get(3)
                .toString().replaceAll("\"", "");
        final short methodRef = ConstantPool.getMethodRef(className, "<init>", type);

        children.add(new CompiledNode(new InvokeSpecial(methodRef)));

        return compiled;
    }
}
