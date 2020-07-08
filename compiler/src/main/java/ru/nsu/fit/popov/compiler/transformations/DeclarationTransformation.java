package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.bytecode.Variables;
import ru.nsu.fit.popov.compiler.types.nonterminal.Declaration;
import ru.nsu.fit.popov.compiler.types.terminal.IntegerType;

public class DeclarationTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof Declaration))
            return node;
        if (node.getParent().getType() instanceof Declaration)
            return node;

        Node last = node;
        for (; last.getChildren().size() == 3; last = last.getChildren().get(0));

        final Type type = last.getChildren().get(0).getType();
        declare(type, last.getChildren().get(1).toString());

        for (last = last.getParent(); last != node.getParent(); last = last.getParent()) {
            declare(type, last.getChildren().get(2).toString());
        }

        return new EmptyNode();
    }

    private void declare(Type type, String var) {
        if (type instanceof IntegerType)
            Variables.putVariable(var, "int");
        else
            Variables.putVariable(var, "");
    }
}
