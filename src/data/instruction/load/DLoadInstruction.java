package data.instruction.load;

import data.ClassFile;
import data.instruction.LoadInstruction;
import data.instruction.Opcode;
import data.instruction.t.DLoad_0Instruction;
import data.instruction.t.DLoad_1Instruction;
import data.instruction.t.DLoad_2Instruction;
import data.instruction.t.DLoad_3Instruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x18, mnemonic = "dload")
public sealed class DLoadInstruction extends LoadInstruction permits DLoad_0Instruction, DLoad_1Instruction, DLoad_2Instruction, DLoad_3Instruction {

    public DLoadInstruction(int lvi) {
        super(lvi);
    }

    public static DLoadInstruction read(DataInputStream in) throws IOException {
        return new DLoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
