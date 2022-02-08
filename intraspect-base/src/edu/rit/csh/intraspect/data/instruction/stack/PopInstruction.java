package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x57, mnemonic = "pop")
public final class PopInstruction extends StackInstruction {

    public PopInstruction() {

    }

    public static PopInstruction read(final DataInputStream in) throws IOException {
        return new PopInstruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        stack.pop();
    }
}
