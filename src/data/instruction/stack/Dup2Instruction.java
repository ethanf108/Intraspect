package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.util.Stack;

@Opcode(opcode = 0x5C, mnemonic = "dup2")
public final class Dup2Instruction extends StackInstruction {

    public Dup2Instruction() {

    }

    @Override
    public <T> void apply(Stack<T> stack) {
        stack.add(stack.size() - 2, stack.get(stack.size() - 2));
        stack.add(stack.size() - 2, stack.peek());
    }
}
