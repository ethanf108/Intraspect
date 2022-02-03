package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.util.Stack;

@Opcode(opcode = 0x59, mnemonic = "dup")
public final class DupInstruction extends StackInstruction {

    public DupInstruction() {

    }

    @Override
    public <T> void apply(Stack<T> stack) {
        stack.push(stack.peek());
    }
}
