package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA9, mnemonic = "ret")
public final class RetInstruction extends Instruction {

    private final int localVariableIndex;

    @AssembleInject
    public RetInstruction(final int localVariableIndex) {
        this.localVariableIndex = localVariableIndex;
    }

    public static RetInstruction read(final DataInputStream in) throws IOException {
        return new RetInstruction(in.readUnsignedByte());
    }

    public int getLocalVariableIndex() {
        return this.localVariableIndex;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.localVariableIndex & 0xFF};
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Add method local variable size checking
        return true;
    }
}
