package data.instruction.misc;

import data.ClassFile;
import data.constant.ClassConstant;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xC0, mnemonic = "checkcast")
public final class CheckCastInstruction extends Instruction {

    private final int classIndex;

    public CheckCastInstruction(int classIndex) {
        this.classIndex = classIndex;
    }

    public static CheckCastInstruction read(DataInputStream in) throws IOException {
        return new CheckCastInstruction(in.readUnsignedShort());
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.classIndex & 0xFF00) >> 8, this.classIndex & 0xFF};
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstantDesc(this.classIndex) instanceof ClassConstant;
    }

    public Optional<ClassConstant> getCastType(ClassFile ref) {
        if (this.classIndex == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(ref.getConstantDesc(this.classIndex) instanceof ClassConstant cc ? cc : null);
    }
}
