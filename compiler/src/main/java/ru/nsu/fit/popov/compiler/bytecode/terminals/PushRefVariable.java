package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  -> ref
 */
public class PushRefVariable extends Terminal {

    private final static byte aload_0   = 0x2a;
    private final static byte aload_1   = 0x2b;
    private final static byte aload_2   = 0x2c;
    private final static byte aload_3   = 0x2d;

    private final static byte aload     = 0x19;

    private static String code(byte var) {
        ByteBuffer buffer;
        if (var <= 3) {
            buffer = ByteBuffer.allocate(1);
            switch (var) {
                case 0:
                    buffer.put(aload_0);
                    break;
                case 1:
                    buffer.put(aload_1);
                    break;
                case 2:
                    buffer.put(aload_2);
                    break;
                case 3:
                    buffer.put(aload_3);
            }
        } else {
            buffer = ByteBuffer.allocate(2);
            buffer.put(aload).put(var);
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PushRefVariable(byte var) {
        super(CompiledType.type, code(var));
    }
}
