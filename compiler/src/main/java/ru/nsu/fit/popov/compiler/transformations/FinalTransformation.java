package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;

import java.util.stream.Collectors;

public class FinalTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (node instanceof CompiledNode)
            return node;

        final CompiledNode compiled = new CompiledNode();
        compiled.setChildren(node.getChildren().stream()
                .filter(CompiledNode.class::isInstance)
                .collect(Collectors.toList()));

        return compiled;
    }
}
