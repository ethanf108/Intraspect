package data.instruction.load;

import data.ClassFile;
import data.instruction.LoadInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x17, mnemonic = "fload")
public sealed class FLoadInstruction extends LoadInstruction permits FLoad_0Instruction, FLoad_1Instruction, FLoad_2Instruction, FLoad_3Instruction {

    public FLoadInstruction(int lvi) {
        super(lvi);
    }

    public static FLoadInstruction read(DataInputStream in) throws IOException {
        return new FLoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
