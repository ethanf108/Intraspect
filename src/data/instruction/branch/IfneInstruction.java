package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9A, mnemonic = "ifne")
public final class IfneInstruction extends IfInstruction {

    public IfneInstruction() {

    }

    public static IfneInstruction read(DataInputStream in) throws IOException {
        return new IfneInstruction();
    }

    @Override
    public boolean test(int value) {
        return value != 0;
    }

}
