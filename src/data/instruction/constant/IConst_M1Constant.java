package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x02, mnemonic = "iconst_m1")
public final class IConst_M1Constant extends IConstInstruction {

    public IConst_M1Constant() {

    }

    @Override
    public Integer getValue() {
        return -1;
    }
}
