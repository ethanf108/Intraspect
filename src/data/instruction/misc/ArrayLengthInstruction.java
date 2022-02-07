package data.instruction.misc;

import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xBE, mnemonic = "arraylength")
public final class ArrayLengthInstruction extends Instruction {

    public ArrayLengthInstruction() {

    }

    public static ArrayLengthInstruction read(DataInputStream in) throws IOException {
        return new ArrayLengthInstruction();
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
