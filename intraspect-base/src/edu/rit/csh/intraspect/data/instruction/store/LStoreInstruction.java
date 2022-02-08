package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x37, mnemonic = "lstore")
public sealed class LStoreInstruction extends StoreInstruction permits LStore_0Instruction, LStore_1Instruction, LStore_2Instruction, LStore_3Instruction {

    public LStoreInstruction(final int lvi) {
        super(lvi);
    }

    public static LStoreInstruction read(final DataInputStream in) throws IOException {
        return new LStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
