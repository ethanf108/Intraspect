package data.instruction.store;

import data.ClassFile;
import data.instruction.Opcode;
import data.instruction.StoreInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x17, mnemonic = "fstore")
public sealed class FStoreInstruction extends StoreInstruction permits FStore_0Instruction, FStore_1Instruction, FStore_2Instruction, FStore_3Instruction {

    public FStoreInstruction(int lvi) {
        super(lvi);
    }

    public static FStoreInstruction read(DataInputStream in) throws IOException {
        return new FStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
