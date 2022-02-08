package edu.rit.csh.intraspect.data.instruction.math.and;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x7e, mnemonic = "iand")
public final class IAndInstruction extends AndInstruction<Integer> {

    public IAndInstruction() {

    }

    public static IAndInstruction read(final DataInputStream in) {
        return new IAndInstruction();
    }
}
