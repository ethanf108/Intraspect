package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.util.Stack;

@Opcode(opcode = 0x5F, mnemonic = "swap")
public final class SwapInstruction extends StackInstruction {

    public SwapInstruction() {

    }

    @Override
    public <T> void apply(Stack<T> stack) {
        stack.add(stack.size() - 1, stack.pop());
    }
}
