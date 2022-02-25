package edu.rit.csh.intraspect.data.instruction.math.div;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x6c, mnemonic = "idiv")
public final class IDivInstruction extends DivInstruction<Integer> {

    @AssembleInject
    public IDivInstruction() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    IDivInstruction read(final DataInputStream in) {
        return new IDivInstruction();
    }
}
