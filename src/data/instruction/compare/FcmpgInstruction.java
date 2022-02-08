package data.instruction.compare;

import data.instruction.CompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x96, mnemonic = "fcmpg")
public final class FcmpgInstruction extends CompareInstruction<Float> {

    public FcmpgInstruction() {

    }

    public static FcmpgInstruction read(final DataInputStream in) throws IOException {
        return new FcmpgInstruction();
    }

    @Override
    public int compare(final Float a, final Float b) {
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
