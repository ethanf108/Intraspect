package data.instruction.branch;

import data.ClassFile;
import data.instruction.BranchInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA8, mnemonic = "jsr")
public final class JsrInstruction extends BranchInstruction {

    public JsrInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static JsrInstruction read(final DataInputStream in) throws IOException {
        return new JsrInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return true;
    }
}
