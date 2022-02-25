package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x17, mnemonic = "fload")
public sealed class FLoadInstruction extends LoadInstruction permits FLoad_0Instruction, FLoad_1Instruction, FLoad_2Instruction, FLoad_3Instruction {

    @AssembleInject
    public FLoadInstruction(final int lvi) {
        super(lvi);
    }

    public static FLoadInstruction read(final DataInputStream in) throws IOException {
        return new FLoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
