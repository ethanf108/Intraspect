package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x85, mnemonic = "i2l")
public final class IntToLongInstruction extends ConversionInstruction {

    @AssembleInject
    public IntToLongInstruction() {
    }

    public static IntToLongInstruction read(final DataInputStream in) throws IOException {
        return new IntToLongInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return long.class;
    }
}
