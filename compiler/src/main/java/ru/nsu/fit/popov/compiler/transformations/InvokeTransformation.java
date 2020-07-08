package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.terminals.InvokeVirtual;
import ru.nsu.fit.popov.compiler.types.nonterminal.DoInvoke;

import java.util.List;

public class InvokeTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof DoInvoke))
            return node;

        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(node.getChildren().get(0));

        short stackDepth = 1;
        if (node.getChildren().size() == 8) {
            final Node argumentsNode = node.getChildren().get(6);
            children.add(argumentsNode);
            stackDepth += ArgumentCounter.getCount(argumentsNode);
        }

        Stack.putDepth(stackDepth);

        final String className = node.getChildren().get(3)
                .toString().replaceAll("\"", "");
        final String methodName = node.getChildren().get(4)
                .toString().replaceAll("\"", "");
        final String type = node.getChildren().get(5)
                .toString().replaceAll("\"", "");
        final short methodRef = ConstantPool.getMethodRef(className, methodName, type);

        children.add(new CompiledNode(new InvokeVirtual(methodRef)));

        return compiled;
    }
}
