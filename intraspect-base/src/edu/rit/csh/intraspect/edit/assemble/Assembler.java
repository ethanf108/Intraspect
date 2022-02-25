package edu.rit.csh.intraspect.edit.assemble;

import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.InstructionCache;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        while (br.ready()) {
            final String line = br.readLine();
            final String[] toks = line.split(",? +");
            instructions.add(create(toks));
        }
        return instructions.toArray(Instruction[]::new);
    }

    public static void main(String[] args) throws Throwable {
        Assembler a = new Assembler(new FileInputStream(new File("C:\\Users\\ethan\\Desktop\\inst.jsm")));
        for (Instruction i : a.read()) {
            System.out.println(i);
        }
    }
}
