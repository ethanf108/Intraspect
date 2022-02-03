package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.util.Stack;

@Opcode(opcode = 0x5E, mnemonic = "dup2_x2")
public final class Dup2_x2Instruction extends StackInstruction {

    public Dup2_x2Instruction() {

    }

    @Override
    public <T> void apply(Stack<T> stack) {
        final T first = stack.peek();
        final T second = stack.get(stack.size() - 2);
        stack.add(stack.size() - 4, second);
        stack.add(stack.size() - 4, first);
    }
}
