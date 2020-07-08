package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.Comma;
import ru.nsu.fit.popov.compiler.types.terminal.IntegerConstant;
import ru.nsu.fit.popov.compiler.types.terminal.StringConstant;
import ru.nsu.fit.popov.compiler.types.terminal.Variable;

public class Arguments extends NonTerminalType {

    public Arguments() {
        final Arguments arguments = (Arguments) BASE_INSTANCES.get(Arguments.class);
        if (arguments == null)
            return;

        final Variable variable = new Variable();
        final StringConstant string = new StringConstant();
        final IntegerConstant integer = new IntegerConstant();
        final Comma comma = new Comma();
        rules = new Type[][]{
                { variable },
                { string },
                { integer },
                { arguments, comma, variable },
                { arguments, comma, string },
                { arguments, comma, integer }
        };

        arguments.rules = rules;
    }
}
