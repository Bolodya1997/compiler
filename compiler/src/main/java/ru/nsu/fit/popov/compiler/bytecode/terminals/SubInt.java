package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  v1, v2 -> result
 */
public class SubInt extends Terminal {

    private static final byte isub = 0x64;

    private static String code() {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(isub);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public SubInt() {
        super(CompiledType.type, code());
    }
}
