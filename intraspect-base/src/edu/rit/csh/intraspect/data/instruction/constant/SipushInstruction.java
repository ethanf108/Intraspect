package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x11, mnemonic = "sipush")
public final class SipushInstruction extends ConstantInstruction {

    private final short shortValue;

    @AssembleInject
    public SipushInstruction(final short shortValue) {
        this.shortValue = shortValue;
    }

    public static SipushInstruction read(final DataInputStream in) throws IOException {
        return new SipushInstruction(in.readShort());
    }

    @Override
    public Class<?> getConstantType() {
        return short.class;
    }

    @Override
    public Short getValue() {
        return this.shortValue;
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.shortValue & 0xFF00) >> 8, this.shortValue & 0xFF};
    }
}
