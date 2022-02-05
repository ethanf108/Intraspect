package data.instruction.compare;

import data.instruction.CompareInstruction;

import java.io.DataInputStream;
import java.io.IOException;

public final class DcmplInstruction extends CompareInstruction<Double> {

    public DcmplInstruction() {

    }

    public static DcmplInstruction read(DataInputStream in) throws IOException {
        return new DcmplInstruction();
    }

    @Override
    public int compare(Double a, Double b) {
        if (a > b) {
            return 1;
        } else if (a.equals(b)) {
            return 0;
        } else if (a < b) {
            return -1;
        } else {
            return -1;
        }
    }
}
