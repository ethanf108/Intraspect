package data.instruction.misc;

import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x00, mnemonic = "nop")
public final class NopInstruction extends Instruction {

    public NopInstruction() {
    }

    public static NopInstruction read(DataInputStream in) throws IOException {
        return new NopInstruction();
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }
}
