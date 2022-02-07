package data.instruction;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public final class InstructionCache {

    private static final Map<String, Class<? extends Instruction>> mnemonicCache;
    private static final Map<Integer, Class<? extends Instruction>> opcodeCache;

    static {
        opcodeCache = new HashMap<>();
        mnemonicCache = new HashMap<>();
        recurseInstructions(Instruction.class);
    }

    private InstructionCache() {

    }

    private static void recurseInstructions(Class<? extends Instruction> base) {
        if (!Instruction.class.isAssignableFrom(base)) {
            return;
        }
        if (base.isAnnotationPresent(Opcode.class)) {
            final Opcode info = base.getAnnotation(Opcode.class);
            try {
                var mm = base.getDeclaredMethod("read", DataInputStream.class);
                if ((mm.getModifiers() & 8) > 0) {
                    opcodeCache.put(info.opcode(), base);
                    mnemonicCache.put(info.mnemonic(), base);
                }
            } catch (NoSuchMethodException e) {
            }
        }
        if (base.isSealed()) {
            for (Class<?> clazz : base.getPermittedSubclasses()) {
                recurseInstructions(clazz.asSubclass(Instruction.class));
            }
        }
    }

    public static Class<? extends Instruction> getByMnemonic(String mnemonic) {
        return mnemonicCache.get(mnemonic);
    }

    public static Class<? extends Instruction> getByOpcode(int opcode) {
        return opcodeCache.get(opcode);
    }

    private static byte[] intArrayToByteArray(int[] arr) {
        ByteBuffer buf = ByteBuffer.allocate(arr.length);
        for (int i : arr) {
            buf.put((byte) (i & 0xFF));
        }
        return buf.array();
    }

    private static <T extends Instruction> T instructionRead(Class<T> clazz, DataInputStream in) throws IOException {
        try {
            final Method read = clazz.getDeclaredMethod("read", DataInputStream.class);
            read.setAccessible(true);
            return clazz.cast(read.invoke(null, in));
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Instruction Class " + clazz.getCanonicalName() + " has no valid read method");
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof IOException ioe) {
                throw ioe;
            }
            throw new RuntimeException(e.getTargetException());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("read method is not able to be accessed");
        }
    }

    public static Instruction fromMnemonic(String mnemonic, int... args) throws IOException {
        return instructionRead(mnemonicCache.get(mnemonic), new DataInputStream(new ByteArrayInputStream(intArrayToByteArray(args))));
    }

    public static Instruction fromOpcode(int opcode, int... args) throws IOException {
        return instructionRead(opcodeCache.get(opcode), new DataInputStream(new ByteArrayInputStream(intArrayToByteArray(args))));
    }

    public static Instruction fromMnemonic(String mnemonic, DataInputStream in) throws IOException {
        return instructionRead(mnemonicCache.get(mnemonic), in);
    }

    public static Instruction fromOpcode(int opcode, DataInputStream in) throws IOException {
        return instructionRead(opcodeCache.get(opcode), in);
    }

    public static Instruction read(DataInputStream in) throws IOException {
        final int opcode = in.readUnsignedByte();
        if (!opcodeCache.containsKey(opcode)) {
            return new UnknownInstruction(opcode);
        }
        return fromOpcode(opcode, in);
    }

}
