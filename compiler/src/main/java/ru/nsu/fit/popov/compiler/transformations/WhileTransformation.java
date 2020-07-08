package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.types.nonterminal.WhileCondition;

public class WhileTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof WhileCondition))
            return node;

        final Node condition = node.getChildren().get(2);

        final WhileNode whileNode = new WhileNode();
        whileNode.getChildren().add(ConditionTransformer.transform(condition));
        whileNode.getChildren().add(node.getChildren().get(4));

        return whileNode;
    }
}
