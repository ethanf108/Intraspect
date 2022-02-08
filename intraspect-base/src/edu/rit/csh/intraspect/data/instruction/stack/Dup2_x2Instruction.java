package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x5E, mnemonic = "dup2_x2")
public final class Dup2_x2Instruction extends StackInstruction {

    public Dup2_x2Instruction() {

    }

    public static Dup2_x2Instruction read(final DataInputStream in) throws IOException {
        return new Dup2_x2Instruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        final T first = stack.peek();
        final T second = stack.get(stack.size() - 2);
        stack.add(stack.size() - 4, second);
        stack.add(stack.size() - 4, first);
    }
}
