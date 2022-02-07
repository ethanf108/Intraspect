package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x5A, mnemonic = "dup_x1")
public final class Dup_x1Instruction extends StackInstruction {

    public Dup_x1Instruction() {

    }

    public static Dup_x1Instruction read(DataInputStream in) throws IOException {
        return new Dup_x1Instruction();
    }

    @Override
    public <T> void apply(Stack<T> stack) {
        stack.add(stack.size() - 2, stack.peek());
    }
}
