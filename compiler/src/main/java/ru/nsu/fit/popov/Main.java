package ru.nsu.fit.popov;

import ru.nsu.fit.popov.compiler.Compiler;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1)
            return;

        final String file = new Scanner(new File(args[0])).useDelimiter("\\Z").next()
                .replaceAll("\n", " ");

        final String className = args[0].replaceAll("\\..*", "");
        final String fileName = className + ".class";

        final Compiler compiler = new Compiler();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            byte[] bytes = compiler.compile(className, "#" + file);
            out.write(bytes);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
