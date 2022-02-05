package data.instruction.store;

import data.ClassFile;
import data.instruction.Opcode;
import data.instruction.StoreInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x39, mnemonic = "dstore")
public sealed class DStoreInstruction extends StoreInstruction permits DStore_0Instruction, DStore_1Instruction, DStore_2Instruction, DStore_3Instruction {

    public DStoreInstruction(int lvi) {
        super(lvi);
    }

    public static DStoreInstruction read(DataInputStream in) throws IOException {
        return new DStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}