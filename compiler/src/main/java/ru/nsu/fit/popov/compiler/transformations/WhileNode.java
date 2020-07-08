package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

class WhileNode extends CompiledNode {

    private static final byte __goto = (byte) 0xa7;

    @Override
    public String toString() {
        final List<Node> children = getChildren();

        byte[] branch = Base64.getDecoder().decode(children.get(0).toString());
        byte[] body = Base64.getDecoder().decode(children.get(1).toString());

        final ByteBuffer buffer = ByteBuffer.allocate(branch.length + 2 + body.length + 3);
        buffer.put(branch).putShort((short) (3 + body.length + 3));
        buffer.put(body);
        buffer.put(__goto).putShort((short) -(branch.length + 2 + body.length));

        return Base64.getEncoder().encodeToString(buffer.array());
    }
}
