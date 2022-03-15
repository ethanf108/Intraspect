package edu.rit.csh.intraspect.edit.assemble;

import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.InstructionCache;
import edu.rit.csh.intraspect.data.instruction.wide.WideIincInstruction;
import edu.rit.csh.intraspect.data.instruction.wide.WideRetInstruction;
import edu.rit.csh.intraspect.data.instruction.wide.load.*;
import edu.rit.csh.intraspect.data.instruction.wide.store.*;
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

    @SuppressWarnings("unchecked")
    private static boolean loadConstructor(String name) {
        Class<? extends Instruction> clazz = InstructionCache.getByMnemonic(name);
        if (clazz == null) {
            throw new IllegalArgumentException("Illegal instruction: '" + name + "'");
        }
        for (Constructor<?> con : clazz.getDeclaredConstructors()) {
            if (con.isAnnotationPresent(AssembleInject.class)) {
                constructorCache.put(name, (Constructor<Instruction>) con);
                return true;
            }
        }
        return false;
    }

    private static Instruction createWide(String[] args) {
        return switch (args[0]) {
            case "iinc_w" -> new WideIincInstruction(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            case "ret_w" -> new WideRetInstruction(Integer.parseInt(args[1]));

            case "aload_w" -> new WideALoadInstruction(Integer.parseInt(args[1]));
            case "dload_w" -> new WideDLoadInstruction(Integer.parseInt(args[1]));
            case "fload_w" -> new WideFLoadInstruction(Integer.parseInt(args[1]));
            case "iload_w" -> new WideILoadInstruction(Integer.parseInt(args[1]));
            case "lload_w" -> new WideLLoadInstruction(Integer.parseInt(args[1]));

            case "astore_w" -> new WideAStoreInstruction(Integer.parseInt(args[1]));
            case "dstore_w" -> new WideDStoreInstruction(Integer.parseInt(args[1]));
            case "fstore_w" -> new WideFStoreInstruction(Integer.parseInt(args[1]));
            case "istore_w" -> new WideIStoreInstruction(Integer.parseInt(args[1]));
            case "lstore_w" -> new WideLStoreInstruction(Integer.parseInt(args[1]));

            case "goto_w", "jsr_w", "ldc_w", "ldc2_w" -> null;
            default -> throw new IllegalArgumentException("Illegal wide instructions");
        };
    }

    private static Instruction create(String... args) {
        if (args[0].endsWith("_w")) {
            Instruction wide = createWide(args);
            if (wide != null) {
                return wide;
            }
        }
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
            } else if (param.getType() == short.class) {
                params.add(Short.parseShort(args[index]));
            } else if (param.getType() == long.class) {
                params.add(Long.parseLong(args[index]));
            } else if (param.getType() == byte.class) {
                params.add(Byte.parseByte(args[index]));
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

    public Instruction[] read(int... args) throws IOException {
        List<Instruction> instructions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(this.in));
        OffsetOutputStream outCounter = new OffsetOutputStream(OutputStream.nullOutputStream());
        Map<String, String> labels = new HashMap<>();
        List<String[]> labelRefs = new ArrayList<>();

        while (br.ready()) {
            final String line = br.readLine();
            if (line.isBlank()) {
                continue;
            }
            String[] toks = line.split("(,? +)|(?=;)");
            for (int i = 0; i < toks.length; i++) {
                if (toks[i].strip().startsWith(";")) {
                    String[] ntoks = new String[i];
                    System.arraycopy(toks, 0, ntoks, 0, i);
                    toks = ntoks;
                }
            }
            if (toks.length == 0) {
                continue;
            }
            if (toks.length == 1 && toks[0].startsWith(":")) {
                if (!toks[0].matches(" *:[a-zA-Z][a-zA-Z0-9_-]*")) {
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
                } else if (toks[i].startsWith("#")) {
                    final int argNum = Integer.parseInt(toks[i].substring(1));
                    try {
                        toks[i] = String.valueOf(args[argNum - 1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new IllegalArgumentException("Invalid arguments index #" + argNum);
                    }
                }
            }
            final Instruction inst = create(toks);
            inst.write(outCounter);
            instructions.add(skipLabel ? null : inst);
        }
        outCounter = new OffsetOutputStream(OutputStream.nullOutputStream());
        int lastIndex = 0;
        for (String[] toks : labelRefs) {
            while (instructions.get(lastIndex) != null) {
                instructions.get(lastIndex).write(outCounter);
                lastIndex++;
            }
            for (int i = 1; i < toks.length; i++) {
                if (toks[i].startsWith(":")) {
                    toks[i] = String.valueOf(Integer.parseInt(labels.get(toks[i])) - outCounter.getTotal());
                }
            }
            instructions.set(lastIndex, create(toks));
        }
        return instructions.toArray(Instruction[]::new);
    }

}
