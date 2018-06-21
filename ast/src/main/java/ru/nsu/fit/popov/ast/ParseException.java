package ru.nsu.fit.popov.ast;

import java.io.IOException;

public class ParseException extends IOException {

    private static String createMessage(String string, int pos, int len, String reason) {
        final String message =
                String.format("Parse error at [%d..%d]: %s", pos, pos + len - 1, reason);
        final String points = pointsAt(pos, len);

        return String.format("%s\n%s\n%s", message, string, points);
    }

    private static String pointsAt(int pos, int len) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            builder.append(' ');
        }

        for (int i = 0; i < len; i++) {
            builder.append('^');
        }

        return builder.toString();
    }

    public ParseException(String string, int pos, int len, String reason) {
        super(createMessage(string, pos, len, reason));
    }
}
