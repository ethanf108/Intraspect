package data.instruction.stack;

import data.instruction.Opcode;
import data.instruction.StackInstruction;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

@Opcode(opcode = 0x58, mnemonic = "pop2")
public final class Pop2Instruction extends StackInstruction {

    public Pop2Instruction() {

    }

    public static Pop2Instruction read(final DataInputStream in) throws IOException {
        return new Pop2Instruction();
    }

    @Override
    public <T> void apply(final Stack<T> stack) {
        stack.pop();
        stack.pop();
    }
}
