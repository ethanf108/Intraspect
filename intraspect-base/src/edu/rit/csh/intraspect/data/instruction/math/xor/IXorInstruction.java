package edu.rit.csh.intraspect.data.instruction.math.xor;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x82, mnemonic = "ixor")
public final class IXorInstruction extends XorInstruction<Integer> {

    public IXorInstruction() {

    }

    public IXorInstruction read(final DataInputStream in) {
        return new IXorInstruction();
    }
}
