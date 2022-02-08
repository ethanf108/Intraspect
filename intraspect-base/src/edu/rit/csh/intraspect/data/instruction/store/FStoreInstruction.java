package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x38, mnemonic = "fstore")
public sealed class FStoreInstruction extends StoreInstruction permits FStore_0Instruction, FStore_1Instruction, FStore_2Instruction, FStore_3Instruction {

    public FStoreInstruction(final int lvi) {
        super(lvi);
    }

    public static FStoreInstruction read(final DataInputStream in) throws IOException {
        return new FStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
