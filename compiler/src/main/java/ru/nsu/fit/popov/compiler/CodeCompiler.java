package ru.nsu.fit.popov.compiler;

import ru.nsu.fit.popov.ast.ParseException;
import ru.nsu.fit.popov.ast.Simplifier;
import ru.nsu.fit.popov.ast.Transformation;
import ru.nsu.fit.popov.ast.types.NonTerminalType;
import ru.nsu.fit.popov.ast.types.Terminal;
import ru.nsu.fit.popov.compiler.transformations.*;
import ru.nsu.fit.popov.compiler.types.nonterminal.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CodeCompiler extends Simplifier {

    @SuppressWarnings("unchecked")
    @Override
    protected Class<? extends NonTerminalType>[] getNonTerminalClasses() {
        return new Class[]{
                Arguments.class,
                Assignment.class,
                Block.class,
                Condition.class,
                Declaration.class,
                DoInvoke.class,
                DoNew.class,
                DoPrint.class,
                IfCondition.class,
                IntegerValue.class,
                Line.class,
                Program.class,
                Root.class,
                VariableType.class,
                WhileCondition.class
        };
    }

    @Override
    protected Class<? extends NonTerminalType> getStartNonTerminalClass() {
        return Root.class;
    }

    @Override
    protected ParseException reportParseTreeError(String input, Terminal[] sequence, int position) {
        return new ParseException("not supported", 0, 0, "not supported");
    }

    @Override
    protected Collection<Transformation> getSingleTimeTransformations() {
        return Stream.of(
                new DeclarationTransformation(),    //  must be first
                new IntegerTransformation(),
                new PrintTransformation(),
                new VariableTransformation(),       //  must be after Declaration, Print
                new NewTransformation(),            //  must be before String
                new InvokeTransformation(),         //  must be before String
                new StringTransformation(),
                new ArithmeticTransformation(),
                new AssignmentTransformation(),     //  must be after Arithmetic, Invoke, New
                new IfTransformation(),
                new WhileTransformation(),
                new FinalTransformation())
                .collect(Collectors.toList());
    }

    @Override
    protected Collection<Transformation> getTransformations() {
        return Collections.emptyList();
    }
}
