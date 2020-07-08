package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.terminals.AddInt;
import ru.nsu.fit.popov.compiler.bytecode.terminals.NegateInt;
import ru.nsu.fit.popov.compiler.bytecode.terminals.SubInt;
import ru.nsu.fit.popov.compiler.types.nonterminal.IntegerValue;
import ru.nsu.fit.popov.compiler.types.terminal.Minus;
import ru.nsu.fit.popov.compiler.types.terminal.OpenBlock;
import ru.nsu.fit.popov.compiler.types.terminal.OpenBracket;
import ru.nsu.fit.popov.compiler.types.terminal.Plus;

import java.util.List;

public class ArithmeticTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof IntegerValue))
            return node;

        final List<Node> children = node.getChildren();
        if (children.size() == 2) {
            if (children.get(0).getType() instanceof Plus)
                return children.get(1);
            return negate(node);
        }
        if (children.size() == 3) {
            if (children.get(0).getType() instanceof OpenBracket)
                return children.get(1);
            if (children.get(1).getType() instanceof Plus)
                return plus(children.get(0), children.get(2));
            if (children.get(1).getType() instanceof Minus)
                return minus(children.get(0), children.get(2));
        }

        return node;
    }

    private Node negate(Node node) {
        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(node.getChildren().get(1));
        children.add(new CompiledNode(new NegateInt()));

        return compiled;
    }

    private Node plus(Node first, Node second) {
        Stack.putDepth((short) 2);

        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(first);
        children.add(second);
        children.add(new CompiledNode(new AddInt()));

        return compiled;
    }

    private Node minus(Node first, Node second) {
        Stack.putDepth((short) 2);

        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(first);
        children.add(second);
        children.add(new CompiledNode(new SubInt()));

        return compiled;
    }

    //  FIXME: add multiplication, division
}
