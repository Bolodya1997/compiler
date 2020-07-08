package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  -> ref, ref
 */
public class NewObj extends Terminal {

    private static final byte __new = (byte) 0xbb;
    private static final byte dup   = (byte) 0x59;

    private static String code(short classRef) {
        final ByteBuffer buffer = ByteBuffer.allocate(3 + 1);
        buffer.put(__new).putShort(classRef);
        buffer.put(dup);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public NewObj(short classRef) {
        super(CompiledType.type, code(classRef));
    }
}
