package ru.nsu.fit.popov.ast;

import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.ast.types.TerminalType;

import java.util.Arrays;
import java.util.stream.Stream;

final class TerminalParser {

    /**
     * Parses input string into array of terminals of given ru.nsu.fit.popov.ast.types.
     *
     * @param types                 acceptable ru.nsu.fit.popov.ast.types for output terminals
     * @param string                string to parse
     * @return                      array of terminals
     *
     * @throws ParseException       if string contains fragment which is not correct for any
     *                              of given ru.nsu.fit.popov.ast.types
     */
    static Terminal[] parseString(TerminalType[] types, String string) throws ParseException {
        String modified = string;
        for (TerminalType type : types) {
            final String toReplace = type.getDefaultValue();
            modified = modified.replace(toReplace, String.format(" %s ", toReplace));
        }

        final String[] words = Arrays.stream(modified
                .split(" "))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        final Stream.Builder<Terminal> terminals = Stream.builder();
        for (String word : words) {
            final TerminalType type = Arrays.stream(types)
                    .filter(terminalType -> terminalType.isCorrect(word))
                    .findFirst().orElse(null);

            if (type == null) {
                int pos = string.indexOf(word);
                throw new ParseException(string, pos, 1, "Cannot parse input fragment");
            }

            terminals.add(new Terminal(type, word));
        }

        return terminals.build()
                .toArray(Terminal[]::new);
    }
}
