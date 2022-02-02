package data.instruction.store;

import data.ClassFile;
import data.instruction.Opcode;
import data.instruction.StoreInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x16, mnemonic = "lstore")
public sealed class LStoreInstruction extends StoreInstruction permits LStore_0Instruction, LStore_1Instruction, LStore_2Instruction, LStore_3Instruction {

    public LStoreInstruction(int lvi) {
        super(lvi);
    }

    public static LStoreInstruction read(DataInputStream in) throws IOException {
        return new LStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
