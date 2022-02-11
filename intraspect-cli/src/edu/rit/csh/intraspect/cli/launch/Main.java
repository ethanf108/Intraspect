package edu.rit.csh.intraspect.cli.launch;

import edu.rit.csh.intraspect.cli.res.Resources;
import edu.rit.csh.intraspect.data.ClassFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            System.err.println("Cannot print ");
        }
    }

}
