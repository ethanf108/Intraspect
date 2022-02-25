package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x10, mnemonic = "bipush")
public final class BipushInstruction extends ConstantInstruction {

    private final byte byteValue;

    @AssembleInject
    public BipushInstruction(final byte byteValue) {
        this.byteValue = byteValue;
    }

    public static BipushInstruction read(final DataInputStream in) throws IOException {
        return new BipushInstruction(in.readByte());
    }

    @Override
    public Class<?> getConstantType() {
        return byte.class;
    }

    @Override
    public Byte getValue() {
        return this.byteValue;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.byteValue & 0xFF};
    }
}
