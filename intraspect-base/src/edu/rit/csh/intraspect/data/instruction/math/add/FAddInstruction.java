package edu.rit.csh.intraspect.data.instruction.math.add;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x62, mnemonic = "fadd")
public final class FAddInstruction extends AddInstruction<Float> {

    @AssembleInject
    public FAddInstruction() {

    }

    public static FAddInstruction read(final DataInputStream in) {
        return new FAddInstruction();
    }
}
