package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x5F, mnemonic = "swap")
public final class SwapInstruction extends StackInstruction {

    public SwapInstruction() {

    }

    public static SwapInstruction read(final DataInputStream in) throws IOException {
        return new SwapInstruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        stack.add(stack.size() - 1, stack.pop());
    }
}
