package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x5B, mnemonic = "dup_x2")
public final class Dup_x2Instruction extends StackInstruction {

    public Dup_x2Instruction() {

    }

    public static Dup_x2Instruction read(DataInputStream in) throws IOException {
        return new Dup_x2Instruction();
    }

    @Override
    public <T> void apply(Stack<T> stack) {
        stack.add(stack.size() - 3, stack.peek());
    }
}
