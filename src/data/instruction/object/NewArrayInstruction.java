package data.instruction.object;

import data.ClassFile;
import data.instruction.Instruction;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

public final class NewArrayInstruction extends Instruction {

    private final int aType;

    public NewArrayInstruction(int aType) {
        this.aType = aType;
    }

    public static NewArrayInstruction read(DataInputStream in) throws IOException {
        return new NewArrayInstruction(in.readUnsignedByte());
    }

    public int getaType() {
        return this.aType;
    }

    public Optional<Class<?>> getClassType() {
        return Optional.ofNullable(switch (this.aType) {
            case 4 -> boolean.class;
            case 5 -> char.class;
            case 6 -> float.class;
            case 7 -> double.class;
            case 8 -> byte.class;
            case 9 -> short.class;
            case 10 -> int.class;
            case 11 -> long.class;
            default -> null;
        });
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.aType & 0xFF};
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return this.getClassType().isPresent();
    }
}
