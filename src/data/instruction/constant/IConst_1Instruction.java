package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x04, mnemonic = "iconst_1")
public final class IConst_1Instruction extends IConstInstruction {

    public IConst_1Instruction() {

    }

    @Override
    public Integer getValue() {
        return 1;
    }
}
