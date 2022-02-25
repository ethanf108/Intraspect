package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x58, mnemonic = "pop2")
public final class Pop2Instruction extends StackInstruction {

    @AssembleInject
    public Pop2Instruction() {

    }

    public static Pop2Instruction read(final DataInputStream in) throws IOException {
        return new Pop2Instruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        stack.pop();
        stack.pop();
    }
}
