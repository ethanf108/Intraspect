package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA8, mnemonic = "jsr")
public final class JsrInstruction extends BranchInstruction {

    @AssembleInject
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
