package data.instruction.control;


import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.Opcode;

@Opcode(opcode = 0xB1, mnemonic = "return")
public sealed class ReturnInstruction extends Instruction permits IReturnInstruction, LReturnInstruction, FReturnInstruction, DReturnInstruction, AReturnInstruction {

    public ReturnInstruction() {

    }

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO check return type
        return false;
    }

    public Class<?> getReturnType() {
        return void.class;
    }
}
