package data.instruction.constant;

import data.instruction.LoadConstantInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x13, mnemonic = "ldc_w")
public final class Ldc_wInstruction extends LoadConstantInstruction {

    public Ldc_wInstruction(int constantPoolIndex) {
        super(constantPoolIndex);
    }

    public static Ldc_wInstruction read(DataInputStream in) throws IOException {
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
