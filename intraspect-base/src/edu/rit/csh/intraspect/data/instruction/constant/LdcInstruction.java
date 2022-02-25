package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.data.instruction.load.LoadConstantInstruction;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x12, mnemonic = "ldc")
public final class LdcInstruction extends LoadConstantInstruction {

    @AssembleInject
    public LdcInstruction(final int constantPoolIndex) {
        super(constantPoolIndex);
    }

    public static LdcInstruction read(final DataInputStream in) throws IOException {
        return new LdcInstruction(in.readUnsignedByte());
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.constantPoolIndex & 0xFF};
    }
}
