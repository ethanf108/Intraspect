package edu.rit.csh.intraspect.data.instruction.compare;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x94, mnemonic = "lcmp")
public final class LcmpInstruction extends CompareInstruction<Long> {

    public LcmpInstruction() {

    }

    public static LcmpInstruction read(final DataInputStream in) throws IOException {
        return new LcmpInstruction();
    }

    @Override
    public int compare(final Long a, final Long b) {
        if (a > b) {
            return 1;
        } else if (a.equals(b)) {
            return 0;
        } else {
            return -1;
        }
    }
}
