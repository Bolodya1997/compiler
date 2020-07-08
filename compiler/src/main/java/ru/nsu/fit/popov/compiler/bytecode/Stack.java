package ru.nsu.fit.popov.compiler.bytecode;

public final class Stack {

    private static short depth = 0;

    public static void putDepth(short depth) {
        if (depth > Stack.depth)
            Stack.depth = depth;
    }

    public static short getDepth() {
        return depth;
    }
}
