package edu.rit.csh.intraspect.data.instruction;

import edu.rit.csh.intraspect.data.instruction.misc.UnknownInstruction;
import edu.rit.csh.intraspect.util.OffsetInputStream;

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

    private static void recurseInstructions(final Class<? extends Instruction> base) {
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
            } catch (NoSuchMethodException ignored) {
            }
        }
        if (base.isSealed()) {
            for (final Class<?> clazz : base.getPermittedSubclasses()) {
                recurseInstructions(clazz.asSubclass(Instruction.class));
            }
        }
    }

    public static Class<? extends Instruction> getByMnemonic(final String mnemonic) {
        return mnemonicCache.get(mnemonic);
    }

    public static Class<? extends Instruction> getByOpcode(final int opcode) {
        return opcodeCache.get(opcode);
    }

    private static byte[] intArrayToByteArray(final int[] arr) {
        final ByteBuffer buf = ByteBuffer.allocate(arr.length);
        for (int i : arr) {
            buf.put((byte) (i & 0xFF));
        }
        return buf.array();
    }

    private static <T extends Instruction> T instructionRead(final Class<T> clazz, final OffsetInputStream in) throws IOException {
        try {
            final Method read = clazz.getDeclaredMethod("read", DataInputStream.class);
            read.setAccessible(true);
            return clazz.cast(read.invoke(null, in));
        } catch (final NoSuchMethodException e) {
            throw new IllegalStateException("Read method not present", e);
        } catch (final InvocationTargetException e) {
            if (e.getTargetException() instanceof IOException ioe) {
                throw ioe;
            }
            throw new RuntimeException(e.getTargetException());
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException("read method is not able to be accessed");
        }
    }

    public static Instruction read(final OffsetInputStream in) throws IOException {
        final int opcode = in.readUnsignedByte();
        if (!opcodeCache.containsKey(opcode)) {
            return new UnknownInstruction(opcode);
        }
        return instructionRead(opcodeCache.get(opcode), in);
    }

}
