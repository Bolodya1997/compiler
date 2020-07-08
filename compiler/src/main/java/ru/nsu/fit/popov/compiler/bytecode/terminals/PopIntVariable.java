package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;


/**
 *  stack (before -> after):
 *  value ->
 */
public class PopIntVariable extends Terminal {

    private final static byte istore_0  = 0x3b;
    private final static byte istore_1  = 0x3c;
    private final static byte istore_2  = 0x3d;
    private final static byte istore_3  = 0x3e;

    private final static byte istore    = 0x36;

    private static String code(byte var) {
        ByteBuffer buffer;
        if (var <= 3) {
            buffer = ByteBuffer.allocate(1);
            switch (var) {
                case 0:
                    buffer.put(istore_0);
                    break;
                case 1:
                    buffer.put(istore_1);
                    break;
                case 2:
                    buffer.put(istore_2);
                    break;
                case 3:
                    buffer.put(istore_3);
            }
        } else {
            buffer = ByteBuffer.allocate(2);
            buffer.put(istore).put(var);
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PopIntVariable(byte var) {
        super(CompiledType.type, code(var));
    }
}
