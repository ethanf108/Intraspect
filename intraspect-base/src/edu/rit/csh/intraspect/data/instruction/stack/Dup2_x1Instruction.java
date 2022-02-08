package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x5D, mnemonic = "dup2_x1")
public final class Dup2_x1Instruction extends StackInstruction {

    public Dup2_x1Instruction() {

    }

    public static Dup2_x1Instruction read(final DataInputStream in) throws IOException {
        return new Dup2_x1Instruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        final T first = stack.peek();
        final T second = stack.get(stack.size() - 2);
        stack.add(stack.size() - 3, second);
        stack.add(stack.size() - 3, first);
    }
}
