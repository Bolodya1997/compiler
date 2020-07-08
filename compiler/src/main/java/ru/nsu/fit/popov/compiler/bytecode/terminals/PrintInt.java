package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  out, value ->
 */
public class PrintInt extends Terminal {

    private static final byte invokevirtual = (byte) 0xb6;

    private static String code() {
        final ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put(invokevirtual).putShort(ConstantPool.PRINT_INT);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PrintInt() {
        super(CompiledType.type, code());
    }
}
