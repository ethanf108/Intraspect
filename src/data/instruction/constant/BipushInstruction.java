package data.instruction.constant;

import data.instruction.ConstantInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x10, mnemonic = "bipush")
public final class BipushInstruction extends ConstantInstruction {

    private final byte byteValue;

    public BipushInstruction(byte byteValue) {
        this.byteValue = byteValue;
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
