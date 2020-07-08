package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.LineEnd;

public class Line extends NonTerminalType {

    public Line() {
        final Line line = (Line) BASE_INSTANCES.get(Line.class);
        if (line == null)
            return;

        final Type declaration = BASE_INSTANCES.get(Declaration.class);
        final Type assignment = BASE_INSTANCES.get(Assignment.class);
        final Type print = BASE_INSTANCES.get(DoPrint.class);
        final Type doInvoke = BASE_INSTANCES.get(DoInvoke.class);
        final LineEnd lineEnd = new LineEnd();
        rules = new Type[][]{
                { declaration, lineEnd },
                { assignment, lineEnd },
                { print, lineEnd },
                { doInvoke, lineEnd }
        };

        line.rules = rules;
    }
}
