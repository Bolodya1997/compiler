package ru.nsu.fit.popov.compiler.transformations;

import ru.nsu.fit.popov.ast.Node;
import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

class CompiledNode extends Node {

    CompiledNode() {
        super(CompiledType.type);
    }

    CompiledNode(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        if (getChildren().isEmpty())
            return super.toString();

        byte[] tmp = getChildren().stream()
                .map(Object::toString)
                .map(Base64.getDecoder()::decode)
                .reduce((bytes, bytes2) -> ByteBuffer.allocate(bytes.length + bytes2.length)
                        .put(bytes).put(bytes2).array())
                .orElse(null);
        return Base64.getEncoder().encodeToString(tmp);
    }
}
