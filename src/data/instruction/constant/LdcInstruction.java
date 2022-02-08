package data.instruction.constant;

import data.instruction.LoadConstantInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x12, mnemonic = "ldc")
public final class LdcInstruction extends LoadConstantInstruction {

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
