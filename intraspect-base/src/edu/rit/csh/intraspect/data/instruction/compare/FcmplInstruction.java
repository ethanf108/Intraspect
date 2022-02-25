package edu.rit.csh.intraspect.data.instruction.compare;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x95, mnemonic = "fcmpl")
public final class FcmplInstruction extends CompareInstruction<Float> {

    @AssembleInject
    public FcmplInstruction() {

    }

    public static FcmplInstruction read(final DataInputStream in) throws IOException {
        return new FcmplInstruction();
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
            return -1;
        }
    }
}
