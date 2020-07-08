package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  v1, v2 ->
 */
public class IfLowerThen extends Terminal {

    private static final byte isub = (byte) 0x64;
    private static final byte iflt = (byte) 0x9b;

    private static String code() {
        final ByteBuffer buffer = ByteBuffer.allocate(1 + 1);
        buffer.put(isub);
        buffer.put(iflt);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public IfLowerThen() {
        super(CompiledType.type, code());
    }
}
