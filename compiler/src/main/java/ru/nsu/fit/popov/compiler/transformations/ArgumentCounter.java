package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;

public final class ArgumentCounter {

    static short getCount(Node argumentNode) {
        short count = 1;
        for (Node node = argumentNode; !node.getChildren().isEmpty();
             node = node.getChildren().get(0)) {
            ++count;
        }

        return count;
    }
}
