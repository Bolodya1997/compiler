package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.Variables;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PushIntVariable;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PushRefVariable;
import ru.nsu.fit.popov.compiler.types.nonterminal.Assignment;
import ru.nsu.fit.popov.compiler.types.terminal.Variable;

public class VariableTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof Variable))
            return node;

        final Node parent = node.getParent();
        if (parent.getType() instanceof Assignment) {
            if (parent.getChildren().get(0) == node)
                return node;
        }

        Stack.putDepth((short) 1);

        final Variables.Var var = Variables.getVariable(node.toString());
        if (var.type.equals("int"))
            return new CompiledNode(new PushIntVariable(var.number));
        else
            return new CompiledNode(new PushRefVariable(var.number));
    }
}
