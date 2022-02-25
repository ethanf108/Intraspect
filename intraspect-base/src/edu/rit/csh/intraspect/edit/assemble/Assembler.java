package edu.rit.csh.intraspect.edit.assemble;

import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.InstructionCache;
import edu.rit.csh.intraspect.util.OffsetOutputStream;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;

public class Assembler {

    private static final Map<String, Constructor<Instruction>> constructorCache = new HashMap<>();

    private final InputStream in;

    public Assembler(InputStream in) {
        this.in = in;
    }

    private static boolean loadConstructor(String name) {
        Class<? extends Instruction> clazz = InstructionCache.getByMnemonic(name);
        for (Constructor<?> con : clazz.getDeclaredConstructors()) {
            if (con.isAnnotationPresent(AssembleInject.class)) {
                constructorCache.put(name, (Constructor<Instruction>) con);
                return true;
            }
        }
        return false;
    }

    private static Instruction create(String... args) {
        if (!constructorCache.containsKey(args[0])) {
            if (!loadConstructor(args[0])) {
                throw new IllegalStateException("Instruction '" + args[0] + "' missing assembly constructor");
            }
        }
        final Constructor<Instruction> constructor = constructorCache.get(args[0]);
        if (constructor.getParameterCount() != args.length - 1) {
            throw new IllegalArgumentException("Invalid number of argument");
        }
        List<Object> params = new ArrayList<>();
        int index = 0;
        for (Parameter param : constructor.getParameters()) {
            index++;
            if (param.getType() == int.class) {
                params.add(Integer.parseInt(args[index]));
            } else if (param.getType() == String.class) {
                params.add(args[index]);
            } else {
                throw new IllegalArgumentException("Invalid parameter type");
            }
        }
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(params.toArray(Object[]::new));
        } catch (InstantiationException e) {
            throw new IllegalStateException("Instruction cannot be instantiated", e);
        } catch (IllegalAccessException e) {
            throw new Error("Constructor not accessible", e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Exception thrown while construction instruction '" + args[0] + "'", e);
        }
    }

    public Instruction[] read() throws IOException {
        List<Instruction> instructions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(this.in));
        OffsetOutputStream outCounter = new OffsetOutputStream(OutputStream.nullOutputStream());
        Map<String, String> labels = new HashMap<>();
        List<String[]> labelRefs = new ArrayList<>();
        while (br.ready()) {
            final String line = br.readLine();
            final String[] toks = line.split(",? +");
            if (toks.length == 1 && toks[0].startsWith(":")) {
                if (!toks[0].matches(":[a-zA-Z][a-zA-Z0-9_-]*")) {
                    throw new IllegalArgumentException("Invalid label name");
                }
                labels.put(toks[0], String.valueOf(outCounter.getTotal()));
                continue;
            }
            boolean skipLabel = false;
            for (int i = 1; i < toks.length; i++) {
                if (toks[i].startsWith(":")) {
                    labelRefs.add(Arrays.copyOf(toks, toks.length));
                    toks[i] = "0";
                    skipLabel = true;
                }
            }
            final Instruction inst = create(toks);
            inst.write(outCounter);
            if (skipLabel) {
                instructions.add(null);
            } else {
                instructions.add(inst);
            }
        }
        int lastIndex = 0;
        for (String[] toks : labelRefs) {
            for (int i = 1; i < toks.length; i++) {
                if (toks[i].startsWith(":")) {
                    toks[i] = labels.get(toks[i]);
                }
            }
            while (instructions.get(lastIndex) != null) {
                lastIndex++;
            }
            instructions.set(lastIndex, create(toks));
        }
        return instructions.toArray(Instruction[]::new);
    }

}
