package data.instruction.load;

import data.ClassFile;
import data.instruction.LoadInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x16, mnemonic = "lload")
public sealed class LLoadInstruction extends LoadInstruction permits LLoad_0Instruction, LLoad_1Instruction, LLoad_2Instruction, LLoad_3Instruction {

    public LLoadInstruction(int lvi) {
        super(lvi);
    }

    public static LLoadInstruction read(DataInputStream in) throws IOException {
        return new LLoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
