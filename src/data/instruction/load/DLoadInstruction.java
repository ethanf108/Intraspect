package data.instruction.load;

import data.ClassFile;
import data.instruction.LoadInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x18, mnemonic = "dload")
public sealed class DLoadInstruction extends LoadInstruction permits DLoad_0Instruction, DLoad_1Instruction, DLoad_2Instruction, DLoad_3Instruction {

    public DLoadInstruction(final int lvi) {
        super(lvi);
    }

    public static DLoadInstruction read(final DataInputStream in) throws IOException {
        return new DLoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
