package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x92, mnemonic = "i2c")
public final class IntToCharInstruction extends ConversionInstruction {

    @AssembleInject
    public IntToCharInstruction() {
    }

    public static IntToCharInstruction read(final DataInputStream in) throws IOException {
        return new IntToCharInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return char.class;
    }
}
