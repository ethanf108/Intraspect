package edu.rit.csh.intraspect.cli.launch;

import edu.rit.csh.intraspect.cli.parse.Arguments;
import edu.rit.csh.intraspect.cli.res.Resources;
import edu.rit.csh.intraspect.data.ClassFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;

public class Main {

    private static final byte[] HELP_TEXT;

    static {
        try {
            HELP_TEXT = Resources.class.getResourceAsStream("help.txt").readAllBytes();
        } catch (IOException e) {
            System.err.println("Error: Cannot read help text file");
            throw new IOError(e);
        }
    }

    private static void printHelp() {
        try {
            System.out.write(HELP_TEXT);
        } catch (IOException e) {
            System.err.println("Cannot print");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            printHelp();
            return;
        }

        final String fileName = args[args.length - 1];

        final Arguments flags = new Arguments();

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].isBlank()) {
                continue;
            }
            try {
                flags.parse(args[i]);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                printHelp();
                System.exit(1);
            }
        }

        File readFile = new File(fileName);
        if (!readFile.exists() && !readFile.getName().endsWith(".class")) {
            readFile = new File(readFile.getCanonicalPath() + ".class");
        }
        ClassFile cf;
        try (FileInputStream in = new FileInputStream(readFile)) {
            cf = ClassFile.readClassFile(in);
        }

        ClassInfoPrinter cip = new ClassInfoPrinter(
                flags.contains("decompile"),
                flags.contains("show-constants"),
                flags.contains("show-attributes"),
                flags.contains("verify")
        );
        cip.printClass(cf, System.out);
    }

}
