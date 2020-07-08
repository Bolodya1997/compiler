package ru.nsu.fit.popov.compiler.bytecode;

import java.util.HashMap;
import java.util.Map;


/**
 * var0 = String[] args
 */
public final class Variables {

    public static class Var {
        public String type;
        public final byte number;

        private Var(String type, byte number) {
            this.type = type;
            this.number = number;
        }
    }

    private static final Map<String, Var> variables = new HashMap<>();

    public static void putVariable(String name, String type) {
        if (variables.containsKey(name))
            throw new RuntimeException(String.format("double declaration: %s", name));

        variables.put(name, new Var(type, (byte) (variables.size() + 1)));
    }

    public static Var getVariable(String name) {
        final Var var = variables.get(name);
        if (var == null)
            throw new RuntimeException(String.format("not defined: %s", name));

        return var;
    }

    public static short getCount() {
        return (short) (variables.size() + 1);
    }
}
