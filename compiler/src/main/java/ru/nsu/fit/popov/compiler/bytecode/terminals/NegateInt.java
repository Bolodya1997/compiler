package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;
/**
 *  stack (before -> after):
 *  v1, v2 -> result
 */
public class NegateInt extends Terminal {

    private static final byte ineg = 0x74;

    private static String code() {
        final ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(ineg);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public NegateInt() {
        super(CompiledType.type, code());
    }
}
