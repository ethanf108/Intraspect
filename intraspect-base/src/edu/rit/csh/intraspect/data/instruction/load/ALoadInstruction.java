package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x19, mnemonic = "aload")
public sealed class ALoadInstruction extends LoadInstruction permits ALoad_0Instruction, ALoad_1Instruction, ALoad_2Instruction, ALoad_3Instruction {

    @AssembleInject
    public ALoadInstruction(final int lvi) {
        super(lvi);
    }

    public static ALoadInstruction read(final DataInputStream in) throws IOException {
        return new ALoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }

}
