package ru.nsu.fit.popov.compiler;

import ru.nsu.fit.popov.ast.ParseException;
import ru.nsu.fit.popov.compiler.bytecode.ConstantPool;
import ru.nsu.fit.popov.compiler.bytecode.Stack;
import ru.nsu.fit.popov.compiler.bytecode.Variables;

import java.nio.ByteBuffer;
import java.util.Base64;

public class Compiler {

    private CodeCompiler codeCompiler = new CodeCompiler();

    public byte[] compile(String name, String input) throws ParseException {

        final ByteBuffer head = ByteBuffer.wrap(new byte[]{
                (byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x32
        });

        final ByteBuffer constructor = ByteBuffer.wrap(new byte[]{
                (byte) 0x00, (byte) 0x21, (byte) 0x00, (byte) 0x02,
                (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02,
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x04,
                (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x01,
                (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x11, (byte) 0x00, (byte) 0x01,
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x05, (byte) 0x2a, (byte) 0xb7,
                (byte) 0x00, (byte) 0x01, (byte) 0xb1, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x09, (byte) 0x00, (byte) 0x07, (byte) 0x00,
                (byte) 0x08, (byte) 0x00, (byte) 0x01, (byte) 0x00,
                (byte) 0x06
        });

        final ByteBuffer end = ByteBuffer.wrap(new byte[]{
                (byte) 0xb1, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00
        });

        ConstantPool.init(name);

        String tmp = codeCompiler.simplify(input);

        final byte[] code = Base64.getDecoder().decode(tmp);
        final byte[] cp = ConstantPool.getBytes();

        final int size = head.capacity() +
                cp.length +
                constructor.capacity() +
                4 + 2 + 2 + 4 +
                code.length +
                end.capacity();

        return ByteBuffer.allocate(size)
                .put(head)
                .put(cp)
                .put(constructor)
                .putInt(8 + code.length + 1 + 4)
                .putShort(Stack.getDepth())
                .putShort(Variables.getCount())
                .putInt(code.length + 1)
                .put(code)
                .put(end)
                .array();
    }
}
