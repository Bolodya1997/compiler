package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  this, ...arguments -> result
 */
public class InvokeSpecial extends Terminal {

    private static final byte invokespecial = (byte) 0xb7;

    private static String code(short methodRef) {
        final ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put(invokespecial).putShort(methodRef);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public InvokeSpecial(short methodRef) {
        super(CompiledType.type, code(methodRef));
    }
}
