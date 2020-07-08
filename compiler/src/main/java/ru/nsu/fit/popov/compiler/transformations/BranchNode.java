package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

class BranchNode extends CompiledNode {

    private int elseOffset = 0;

    void addElse() {
        elseOffset = 1 + 2;
    }

    @Override
    public String toString() {
        final List<Node> children = getChildren();

        byte[] branch = Base64.getDecoder().decode(children.get(0).toString());
        byte[] body = Base64.getDecoder().decode(children.get(1).toString());

        final ByteBuffer buffer = ByteBuffer.allocate(branch.length + 2 + body.length);
        buffer.put(branch).putShort((short) (3 + body.length + elseOffset));
        buffer.put(body);

        return Base64.getEncoder().encodeToString(buffer.array());
    }
}
