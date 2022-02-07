package data.instruction.constant;

import data.instruction.ConstantInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x11, mnemonic = "sipush")
public final class SipushInstruction extends ConstantInstruction {

    private final short shortValue;

    public SipushInstruction(short shortValue) {
        this.shortValue = shortValue;
    }

    public static SipushInstruction read(DataInputStream in) throws IOException {
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
