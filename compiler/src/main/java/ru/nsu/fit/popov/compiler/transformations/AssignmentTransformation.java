package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.Variables;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PopIntVariable;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PopRefVariable;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PushIntVariable;
import ru.nsu.fit.popov.compiler.types.nonterminal.Assignment;

public class AssignmentTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof Assignment))
            return node;

        final CompiledNode compiled = new CompiledNode();

        Node push = node.getChildren().get(2);
        if (!(push instanceof CompiledNode))    //  push.type == variable
            push = compile(push);
        compiled.getChildren().add(push);

        final String name = node.getChildren().get(0).toString();
        final Variables.Var var = Variables.getVariable(name);

        CompiledNode pop;
        if (var.type.equals("int"))
            pop = new CompiledNode(new PopIntVariable(var.number));
        else
            pop = new CompiledNode(new PopRefVariable(var.number));
        compiled.getChildren().add(pop);

        return compiled;
    }

    private Node compile(Node node) {
        final Variables.Var var = Variables.getVariable(node.toString());
        if (var.type.equals("int"))
            return new CompiledNode(new PushIntVariable(var.number));
        else
            return new CompiledNode(new PushIntVariable(var.number));
    }
}
