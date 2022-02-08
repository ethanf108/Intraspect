package data.instruction.compare;

import data.instruction.CompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x98, mnemonic = "dcmpg")
public final class DcmpgInstruction extends CompareInstruction<Double> {

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
