package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.Variables;
import ru.nsu.fit.popov.compiler.bytecode.terminals.*;
import ru.nsu.fit.popov.compiler.types.nonterminal.DoPrint;
import ru.nsu.fit.popov.compiler.types.terminal.StringConstant;
import ru.nsu.fit.popov.compiler.types.terminal.Variable;

import java.util.List;

public class PrintTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof DoPrint))
            return node;

        Stack.putDepth((short) 2);

        final List<Node> children = node.getChildren();
        if (children.size() == 3) {
            return empty();
        }

        final Node toPrint = children.get(2);
        if (toPrint.getType() instanceof StringConstant)
            return string(toPrint);
        if (toPrint.getType() instanceof Variable)
            return variable(toPrint);

        return integer(toPrint);
    }

    private Node empty() {
        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(new CompiledNode(new SysOut()));
        children.add(new CompiledNode(new PushStringRef("")));
        children.add(new CompiledNode(new PrintString()));

        return compiled;
    }

    private Node string(Node stringNode) {
        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(new CompiledNode(new SysOut()));
        children.add(stringNode);
        children.add(new CompiledNode(new PrintString()));

        return compiled;
    }

    private Node variable(Node varNode) {
        final CompiledNode compiled = new CompiledNode();

        final Variables.Var var = Variables.getVariable(varNode.toString());

        final List<Node> children = compiled.getChildren();
        if (!var.type.equals("int")) {
            children.add(new CompiledNode(new SysOut()));
            children.add(new CompiledNode(new PushRefVariable(var.number)));
            children.add(new CompiledNode(new PrintString()));
        } else {
            children.add(new CompiledNode(new SysOut()));
            children.add(new CompiledNode(new PushIntVariable(var.number)));
            children.add(new CompiledNode(new PrintInt()));
        }

        return compiled;
    }

    private Node integer(Node intNode) {
        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        children.add(new CompiledNode(new SysOut()));
        children.add(intNode);
        children.add(new CompiledNode(new PrintInt()));

        return compiled;
    }
}
