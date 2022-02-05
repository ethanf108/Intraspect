package data.instruction.math;

import data.instruction.Opcode;

@Opcode(opcode = 0x60, mnemonic = "iadd")
public final class IAdd extends AddInstruction<Integer> {

    @Override
    public Integer apply(final Integer a, final Integer b) {
        return a + b;
    }
}
