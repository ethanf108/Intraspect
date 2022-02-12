package edu.rit.csh.intraspect.cli.parse;

import edu.rit.csh.intraspect.cli.res.Resources;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Arguments {

    private static final Argument[] ARGUMENT_LIST;

    private static final Map<String, Argument> argCache;

    static {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Resources.class.getResourceAsStream("commands.txt")))) {
            ARGUMENT_LIST = br.lines().map(Argument::parse).toArray(Argument[]::new);
            argCache = new HashMap<>();
            for (Argument a : ARGUMENT_LIST) {
                for (String value : a.values()) {
                    if (argCache.containsKey(value)) {
                        throw new IllegalStateException("Invalid Argument file");
                    }
                    argCache.put(value, a);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Arguments from file. Abort.");
            throw new IOError(e);
        }
    }

    private final Set<String> arguments;

    public Arguments() {
        this.arguments = new HashSet<>();
    }

    public boolean parse(String... args) {
        for (String arg : args) {
            if (!argCache.containsKey(arg)) {
                return false;
            }
            this.arguments.add(argCache.get(arg).name());
        }
        return true;
    }

    public boolean contains(String name) {
        return this.arguments.contains(name);
    }

    private record Argument(String name, int numArgs, String[] values) {

        static Argument parse(String str) {
            final String[] args = str.split(" ");
            if (args.length < 3) {
                throw new IllegalArgumentException("Invalid number of arguments");
            }
            final String[] values = new String[args.length - 2];
            System.arraycopy(args, 2, values, 0, values.length);
            return new Argument(args[0], Integer.parseInt(args[1]), values);
        }
    }

}
