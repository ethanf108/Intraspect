package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x5A, mnemonic = "dup_x1")
public final class Dup_x1Instruction extends StackInstruction {

    public Dup_x1Instruction() {

    }

    public static Dup_x1Instruction read(final DataInputStream in) throws IOException {
        return new Dup_x1Instruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        stack.add(stack.size() - 2, stack.peek());
    }
}
