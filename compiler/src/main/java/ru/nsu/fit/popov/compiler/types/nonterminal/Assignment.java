package ru.nsu.fit.popov.compiler.types.nonterminal;

import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Type;
import ru.nsu.fit.popov.compiler.types.terminal.Is;
import ru.nsu.fit.popov.compiler.types.terminal.StringConstant;
import ru.nsu.fit.popov.compiler.types.terminal.Variable;

public class Assignment extends NonTerminalType {

    public Assignment() {
        final Assignment assignment = (Assignment) BASE_INSTANCES.get(Assignment.class);
        if (assignment == null)
            return;

        final Variable variable = new Variable();
        final Is is = new Is();
        final Type integerValue = BASE_INSTANCES.get(IntegerValue.class);
        final Type doInvoke = BASE_INSTANCES.get(DoInvoke.class);
        final Type doNew = BASE_INSTANCES.get(DoNew.class);
        rules = new Type[][]{
                { variable, is, integerValue },
                { variable, is, new StringConstant() },
                { variable, is, doInvoke },
                { variable, is, doNew },
                { variable, is, variable }
        };

        assignment.rules = rules;
    }
}
