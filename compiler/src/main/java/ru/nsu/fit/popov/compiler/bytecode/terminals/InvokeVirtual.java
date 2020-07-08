package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  this, ...arguments -> result
 */
public class InvokeVirtual extends Terminal {

    private static final byte invokevirtual = (byte) 0xb6;

    private static String code(short methodRef) {
        final ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put(invokevirtual).putShort(methodRef);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public InvokeVirtual(short methodRef) {
        super(CompiledType.type, code(methodRef));
    }
}
