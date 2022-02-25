package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x36, mnemonic = "istore")
public sealed class IStoreInstruction extends StoreInstruction permits IStore_0Instruction, IStore_1Instruction, IStore_2Instruction, IStore_3Instruction {

    @AssembleInject
    public IStoreInstruction(final int lvi) {
        super(lvi);
    }

    public static IStoreInstruction read(final DataInputStream in) throws IOException {
        return new IStoreInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }
}
