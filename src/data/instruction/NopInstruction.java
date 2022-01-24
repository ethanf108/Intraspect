package data.instruction;

import data.ClassFile;
import java.io.DataOutputStream;
import java.io.IOException;

@Opcode(opcode = 0x00, mnemonic = "nop")
public final class NopInstruction extends Instruction {

    public NopInstruction() {
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getOpcode());
    }

}
