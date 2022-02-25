package edu.rit.csh.intraspect.data.instruction.compare;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x98, mnemonic = "dcmpg")
public final class DcmpgInstruction extends CompareInstruction<Double> {

    @AssembleInject
    public DcmpgInstruction() {

    }

    public static DcmpgInstruction read(final DataInputStream in) throws IOException {
        return new DcmpgInstruction();
    }

    @Override
    public int compare(final Double a, final Double b) {
        if (a > b) {
            return 1;
        } else if (a.equals(b)) {
            return 0;
        } else if (a < b) {
            return -1;
        } else {
            return 1;
        }
    }
}
