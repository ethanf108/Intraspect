package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x39, mnemonic = "dstore")
public sealed class DStoreInstruction extends StoreInstruction permits DStore_0Instruction, DStore_1Instruction, DStore_2Instruction, DStore_3Instruction {

    public DStoreInstruction(final int lvi) {
        super(lvi);
    }

    public static DStoreInstruction read(final DataInputStream in) throws IOException {
        return new DStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
