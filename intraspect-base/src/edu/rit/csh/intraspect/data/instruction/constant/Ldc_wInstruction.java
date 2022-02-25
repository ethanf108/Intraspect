package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.data.instruction.load.LoadConstantInstruction;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x13, mnemonic = "ldc_w")
public final class Ldc_wInstruction extends LoadConstantInstruction {

    @AssembleInject
    public Ldc_wInstruction(final int constantPoolIndex) {
        super(constantPoolIndex);
    }

    public static Ldc_wInstruction read(final DataInputStream in) throws IOException {
        return new Ldc_wInstruction(in.readUnsignedShort());
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.constantPoolIndex & 0xFF00) >> 8, this.constantPoolIndex & 0xFF};
    }

}
