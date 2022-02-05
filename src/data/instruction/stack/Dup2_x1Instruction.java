package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.util.Stack;

@Opcode(opcode = 0x5D, mnemonic = "dup2_x1")
public final class Dup2_x1Instruction extends StackInstruction {

    public Dup2_x1Instruction() {

    }

    @Override
    public <T> void apply(Stack<T> stack) {
        final T first = stack.peek();
        final T second = stack.get(stack.size() - 2);
        stack.add(stack.size() - 3, second);
        stack.add(stack.size() - 3, first);
    }
}
