package data.instruction.math.xor;

import data.instruction.Opcode;
import data.instruction.math.XorInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x82, mnemonic = "ixor")
public final class IXor extends XorInstruction<Integer> {

    public IXor() {

    }

    public IXor read(final DataInputStream in) {
        return new IXor();
    }
}
