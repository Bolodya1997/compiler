package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.compiler.bytecode.terminals.GoTo;
import ru.nsu.fit.popov.compiler.types.nonterminal.IfCondition;

import java.util.List;

public class IfTransformation implements Transformation {

    @Override
    public Node perform(Node node) {
        if (!(node.getType() instanceof IfCondition))
            return node;

        final List<Node> children = node.getChildren();
        if (children.size() == 5)
            return ifNode(node);

        final BranchNode ifNode = (BranchNode) children.get(0);
        ifNode.addElse();

        final CompiledNode compiled = new CompiledNode();
        compiled.getChildren().add(ifNode);
        compiled.getChildren().add(elseNode(children.get(2)));

        return compiled;
    }

    private BranchNode ifNode(Node node) {
        final Node condition = node.getChildren().get(2);

        final BranchNode ifNode = new BranchNode();
        ifNode.getChildren().add(ConditionTransformer.transform(condition));
        ifNode.getChildren().add(node.getChildren().get(4));

        return ifNode;
    }

    private BranchNode elseNode(Node body) {
        final BranchNode elseNode = new BranchNode();

        final List<Node> children = elseNode.getChildren();
        children.add(new CompiledNode(new GoTo()));
        children.add(body);

        return elseNode;
    }
}
