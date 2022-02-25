package edu.rit.csh.intraspect.data.instruction.misc;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x84, mnemonic = "iinc")
public final class IincInstruction extends Instruction {

    private final int localVariableIndex;
    private final int constant;

    @AssembleInject
    public IincInstruction(final int localVariableIndex, final int constant) {
        this.localVariableIndex = localVariableIndex;
        this.constant = constant;
    }

    public static IincInstruction read(final DataInputStream in) throws IOException {
        return new IincInstruction(in.readUnsignedByte(), in.readUnsignedByte());
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.localVariableIndex & 0xFF, this.constant & 0xFF};
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO check for local variable bounds
        return true;
    }
}
