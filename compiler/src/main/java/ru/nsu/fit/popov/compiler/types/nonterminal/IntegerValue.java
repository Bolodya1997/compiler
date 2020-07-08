package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.*;

public class IntegerValue extends NonTerminalType {

    public IntegerValue() {
        final IntegerValue value = (IntegerValue) BASE_INSTANCES.get(IntegerValue.class);
        if (value == null)
            return;

        final IntegerConstant constant = new IntegerConstant();
        final Variable variable = new Variable();
        final Plus plus = new Plus();
        final Minus minus = new Minus();
        rules = new Type[][]{
                { constant },
                { plus, constant },
                { minus, constant },
                { variable },
                { plus, variable },
                { minus, variable },
                { value, plus, value },
                { value, minus, value },
                { new OpenBracket(), value, new CloseBracket() }
        };

        value.rules = rules;
    }
}
