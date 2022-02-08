package edu.rit.csh.intraspect.data.instruction.misc;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xBF, mnemonic = "athrow")
public final class AThrowInstruction extends Instruction {

    public AThrowInstruction() {

    }

    public static AThrowInstruction read(final DataInputStream in) throws IOException {
        return new AThrowInstruction();
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
