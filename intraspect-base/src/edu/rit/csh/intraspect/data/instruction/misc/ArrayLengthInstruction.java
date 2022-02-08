package edu.rit.csh.intraspect.data.instruction.misc;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xBE, mnemonic = "arraylength")
public final class ArrayLengthInstruction extends Instruction {

    public ArrayLengthInstruction() {

    }

    public static ArrayLengthInstruction read(final DataInputStream in) throws IOException {
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
    public boolean isValid(final ClassFile ref) {
        return true;
    }
}
