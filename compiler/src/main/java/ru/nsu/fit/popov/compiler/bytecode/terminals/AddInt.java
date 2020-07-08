package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  v1, v2 -> result
 */
public class AddInt extends Terminal {

    private static final byte iadd = 0x60;

    private static String code() {
        final ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(iadd);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public AddInt() {
        super(CompiledType.type, code());
    }
}
