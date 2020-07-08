package ru.nsu.fit.popov.compiler.bytecode.terminals;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.types.nonterminal.CompiledType;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 *  stack (before -> after):
 *  -> value
 */
public class PushIntValue extends Terminal {

    private final static byte iconst_m1 = 0x02;
    private final static byte iconst_0  = 0x03;
    private final static byte iconst_1  = 0x04;
    private final static byte iconst_2  = 0x05;
    private final static byte iconst_3  = 0x06;
    private final static byte iconst_4  = 0x07;
    private final static byte iconst_5  = 0x08;

    private final static byte bipush    = 0x10;
    private final static byte sipush    = 0x11;

    private static final byte ldc       = 0x12;
    private static final byte ldc_w     = 0x13;

    private static String code(int value) {
        ByteBuffer buffer;
        if (-1 <= value && value <= 5) {
            buffer = ByteBuffer.allocate(1);
            switch (value) {
                case -1:
                    buffer.put(iconst_m1);
                    break;
                case 0:
                    buffer.put(iconst_0);
                    break;
                case 1:
                    buffer.put(iconst_1);
                    break;
                case 2:
                    buffer.put(iconst_2);
                    break;
                case 3:
                    buffer.put(iconst_3);
                    break;
                case 4:
                    buffer.put(iconst_4);
                    break;
                case 5:
                    buffer.put(iconst_5);
            }
        } else if (Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE) {
            buffer = ByteBuffer.allocate(2);
            buffer.put(bipush).put((byte) value);
        } else if (Short.MAX_VALUE <= value && value <= Short.MAX_VALUE) {
            buffer = ByteBuffer.allocate(3);
            buffer.put(sipush).putShort((short) value);
        } else {
            final short ref = ConstantPool.getIntegerRef(value);
            if (Byte.MIN_VALUE <= ref && ref <= Byte.MAX_VALUE) {
                buffer = ByteBuffer.allocate(2);
                buffer.put(ldc).put((byte) ref);
            } else {
                buffer = ByteBuffer.allocate(3);
                buffer.put(ldc_w).putShort(ref);
            }
        }

        return Base64.getEncoder().encodeToString(buffer.array());
    }

    public PushIntValue(int value) {
        super(CompiledType.type, code(value));
    }
}
