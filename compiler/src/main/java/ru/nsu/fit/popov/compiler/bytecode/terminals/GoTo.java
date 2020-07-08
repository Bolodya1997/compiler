package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  ->
 */
public class GoTo extends Terminal {

    private static final byte __goto = (byte) 0xa7;

    private static String code() {
        final ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(__goto);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public GoTo() {
        super(CompiledType.type, code());
    }
}
