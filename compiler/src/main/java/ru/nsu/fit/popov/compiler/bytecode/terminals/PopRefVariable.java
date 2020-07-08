package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  ref ->
 */
public class PopRefVariable extends Terminal {

    private final static byte astore_0  = 0x4b;
    private final static byte astore_1  = 0x4c;
    private final static byte astore_2  = 0x4d;
    private final static byte astore_3  = 0x4e;

    private final static byte astore    = 0x3a;

    private static String code(byte var) {
        ByteBuffer buffer;
        if (var <= 3) {
            buffer = ByteBuffer.allocate(1);
            switch (var) {
                case 0:
                    buffer.put(astore_0);
                    break;
                case 1:
                    buffer.put(astore_1);
                    break;
                case 2:
                    buffer.put(astore_2);
                    break;
                case 3:
                    buffer.put(astore_3);
            }
        } else {
            buffer = ByteBuffer.allocate(2);
            buffer.put(astore).put(var);
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PopRefVariable(byte var) {
        super(CompiledType.type, code(var));
    }
}
