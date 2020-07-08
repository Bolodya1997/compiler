package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  -> local[var]
 */
public class PushIntVariable extends Terminal {

    private final static byte iload_0   = 0x1a;
    private final static byte iload_1   = 0x1b;
    private final static byte iload_2   = 0x1c;
    private final static byte iload_3   = 0x1d;

    private final static byte iload     = 0x15;

    private static String code(byte var) {
        ByteBuffer buffer;
        if (var <= 3) {
            buffer = ByteBuffer.allocate(1);
            switch (var) {
                case 0:
                    buffer.put(iload_0);
                    break;
                case 1:
                    buffer.put(iload_1);
                    break;
                case 2:
                    buffer.put(iload_2);
                    break;
                case 3:
                    buffer.put(iload_3);
            }
        } else {
            buffer = ByteBuffer.allocate(2);
            buffer.put(iload).put(var);
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PushIntVariable(byte var) {
        super(CompiledType.type, code(var));
    }
}
