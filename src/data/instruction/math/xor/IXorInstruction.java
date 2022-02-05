package data.instruction.math.xor;

import data.instruction.Opcode;
import data.instruction.math.XorInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x82, mnemonic = "ixor")
public final class IXorInstruction extends XorInstruction<Integer> {

    public IXorInstruction() {

    }

    public IXorInstruction read(final DataInputStream in) {
        return new IXorInstruction();
    }
}
