package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  -> stringRef
 */
public class PushStringRef extends Terminal {

    private static final byte ldc   = 0x12;
    private static final byte ldc_w = 0x13;

    private static String code(String string) {
        final short ref = ConstantPool.getStringRef(string);

        ByteBuffer buffer;
        if (Byte.MIN_VALUE <= ref && ref <= Byte.MAX_VALUE) {
            buffer = ByteBuffer.allocate(2);
            buffer.put(ldc).put((byte) ref);
        } else {
            buffer = ByteBuffer.allocate(3);
            buffer.put(ldc_w).putShort(ref);
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PushStringRef(String string) {
        super(CompiledType.type, code(string));
    }
}
