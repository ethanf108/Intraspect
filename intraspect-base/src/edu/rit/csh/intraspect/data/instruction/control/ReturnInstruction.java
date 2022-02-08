package edu.rit.csh.intraspect.data.instruction.control;


import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB1, mnemonic = "return")
public sealed class ReturnInstruction extends Instruction permits IReturnInstruction, LReturnInstruction, FReturnInstruction, DReturnInstruction, AReturnInstruction {

    public ReturnInstruction() {

    }

    public static ReturnInstruction read(final DataInputStream in) throws IOException {
        return new ReturnInstruction();
    }

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO check return type
        return true;
    }

    public Class<?> getReturnType() {
        return void.class;
    }
}
