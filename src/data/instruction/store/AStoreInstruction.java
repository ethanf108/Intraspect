package data.instruction.store;

import data.ClassFile;
import data.instruction.Opcode;
import data.instruction.StoreInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3A, mnemonic = "astore")
public sealed class AStoreInstruction extends StoreInstruction permits AStore_0Instruction, AStore_1Instruction, AStore_2Instruction, AStore_3Instruction {

    public AStoreInstruction(final int lvi) {
        super(lvi);
    }

    public static AStoreInstruction read(final DataInputStream in) throws IOException {
        return new AStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }

}
