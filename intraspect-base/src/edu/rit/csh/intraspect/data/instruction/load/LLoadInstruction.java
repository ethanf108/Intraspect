package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x16, mnemonic = "lload")
public sealed class LLoadInstruction extends LoadInstruction permits LLoad_0Instruction, LLoad_1Instruction, LLoad_2Instruction, LLoad_3Instruction {

    @AssembleInject
    public LLoadInstruction(final int lvi) {
        super(lvi);
    }

    public static LLoadInstruction read(final DataInputStream in) throws IOException {
        return new LLoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
