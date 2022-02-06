package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x57, mnemonic = "pop")
public final class PopInstruction extends StackInstruction {

    public PopInstruction() {

    }

    public static PopInstruction read(DataInputStream in) throws IOException {
        return new PopInstruction();
    }

    @Override
    public <T> void apply(Stack<T> stack) {
        stack.pop();
    }
}
