package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x15, mnemonic = "iload")
public sealed class ILoadInstruction extends LoadInstruction permits ILoad_0Instruction, ILoad_1Instruction, ILoad_2Instruction, ILoad_3Instruction {

    public ILoadInstruction(final int lvi) {
        super(lvi);
    }

    public static ILoadInstruction read(final DataInputStream in) throws IOException {
        return new ILoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }
}
