package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x59, mnemonic = "dup")
public final class DupInstruction extends StackInstruction {

    public DupInstruction() {

    }

    public static DupInstruction read(final DataInputStream in) throws IOException {
        return new DupInstruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        stack.push(stack.peek());
    }
}
