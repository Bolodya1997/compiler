package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

public class SysOut extends Terminal {

    private static final byte getstatic = (byte) 0xb2;

    private static String code() {
        final ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put(getstatic).putShort(ConstantPool.SYSTEM_OUT);

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public SysOut() {
        super(CompiledType.type, code());
    }
}