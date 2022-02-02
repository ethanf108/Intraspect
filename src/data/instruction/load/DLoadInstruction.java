package data.instruction.load;

import data.ClassFile;
import data.instruction.LoadInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x18, mnemonic = "dload")
public final class DLoadInstruction extends LoadInstruction {

    public DLoadInstruction(int lvi) {
        super(lvi);
    }

    public static DLoadInstruction read(DataInputStream in) throws IOException {
        return new DLoadInstruction(in.readUnsignedByte());
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
