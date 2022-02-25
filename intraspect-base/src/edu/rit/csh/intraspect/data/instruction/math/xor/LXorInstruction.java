package edu.rit.csh.intraspect.data.instruction.math.xor;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x83, mnemonic = "lxor")
public final class LXorInstruction extends XorInstruction<Long> {

    @AssembleInject
    public LXorInstruction() {

    }

    public LXorInstruction read(final DataInputStream in) {
        return new LXorInstruction();
    }
}
