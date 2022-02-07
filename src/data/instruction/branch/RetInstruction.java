package data.instruction.branch;

import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA9, mnemonic = "ret")
public final class RetInstruction extends Instruction {

    private final int localVariableIndex;

    public RetInstruction(int localVariableIndex) {
        this.localVariableIndex = localVariableIndex;
    }

    public static RetInstruction read(DataInputStream in) throws IOException {
        return new RetInstruction(in.readUnsignedByte());
    }

    public int getLocalVariableIndex() {
        return this.localVariableIndex;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.localVariableIndex & 0xFF};
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Add method local variable size checking
        return true;
    }
}
