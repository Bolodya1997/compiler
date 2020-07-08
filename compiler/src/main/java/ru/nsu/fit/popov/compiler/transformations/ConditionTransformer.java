package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.terminals.IfEquals;
import ru.nsu.fit.popov.compiler.bytecode.terminals.IfLowerThen;
import ru.nsu.fit.popov.compiler.bytecode.terminals.IfNotEquals;
import ru.nsu.fit.popov.compiler.bytecode.terminals.PushIntValue;
import ru.nsu.fit.popov.compiler.types.terminal.Equals;
import ru.nsu.fit.popov.compiler.types.terminal.NotEquals;
import ru.nsu.fit.popov.compiler.types.terminal.True;

import java.util.List;

final class ConditionTransformer {

    static CompiledNode transform(Node condition) {
        if (condition.getChildren().size() == 0)
            return primitive(condition);

        final CompiledNode compiled = new CompiledNode();
        compiled.getChildren().add(condition.getChildren().get(0));
        compiled.getChildren().add(condition.getChildren().get(2));

        final Node pureCondition = condition.getChildren().get(1);
        if (pureCondition.getType() instanceof Equals)
            compiled.getChildren().add(equals());
        else if (pureCondition.getType() instanceof NotEquals)
            compiled.getChildren().add(notEquals());
        else
            compiled.getChildren().add(greaterEquals());

        return compiled;
    }

    private static CompiledNode primitive(Node primitive) {
        Stack.putDepth((short) 2);

        final CompiledNode compiled = new CompiledNode();

        final List<Node> children = compiled.getChildren();
        if (primitive.getType() instanceof True) {
            children.add(new CompiledNode(new PushIntValue(0)));
            children.add(new CompiledNode(new PushIntValue(0)));
        } else {
            children.add(new CompiledNode(new PushIntValue(0)));
            children.add(new CompiledNode(new PushIntValue(1)));
        }

        children.add(equals());

        return compiled;
    }

    private static CompiledNode equals() {
        return new CompiledNode(new IfNotEquals());
    }

    private static CompiledNode notEquals() {
        return new CompiledNode(new IfEquals());
    }

    private static CompiledNode greaterEquals() {
        return new CompiledNode(new IfLowerThen());
    }
}
